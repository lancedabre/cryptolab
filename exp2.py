# toy_rsa_sign_verify.py
import hashlib
import sys

def egcd(a, b):
    if a == 0:
        return (b, 0, 1)
    else:
        g, y, x = egcd(b % a, a)
        return (g, x - (b // a) * y, y)

def modinv(a, m):
    g, x, y = egcd(a, m)
    if g != 1:
        raise Exception('Modular inverse does not exist')
    return x % m

def gcd(a, b):
    while b:
        a, b = b, a % b
    return a

def int_from_hash(msg: bytes, hash_name="sha256") -> int:
    h = hashlib.new(hash_name, msg).digest()
    return int.from_bytes(h, byteorder="big")

def hex_from_int(x: int) -> str:
    return hex(x)[2:]

def main():
    try:
        p = int(input("Enter prime number p (for demo only, small primes OK): ").strip())
        q = int(input("Enter prime number q (for demo only, small primes OK): ").strip())
    except ValueError:
        print("Invalid integer input.")
        sys.exit(1)

    if p == q:
        print("p and q must differ.")
        sys.exit(1)

    n = p * q
    phi = (p - 1) * (q - 1)
    print(f"\nn = {n}")
    print(f"φ(n) = {phi}")

    # choose e (65537 is common); ensure gcd(e, phi) == 1
    default_e = 65537
    if gcd(default_e, phi) == 1:
        e = default_e
        print(f"Using standard public exponent e = {e}")
    else:
        # fallback: ask user
        while True:
            try:
                e = int(input(f"Enter public exponent e (1 < e < {phi}, coprime with {phi}): ").strip())
            except ValueError:
                print("Enter a valid integer.")
                continue
            if 1 < e < phi and gcd(e, phi) == 1:
                break
            print(f"Invalid e. Ensure 1 < e < {phi} and gcd(e, {phi}) == 1.")

    d = modinv(e, phi)
    print("\nKeys Generated:")
    print("Public Key (e, n):", (e, n))
    print("Private Key (d, n):", (d, n))

    message = input("\nEnter message (text) to sign: ").encode("utf-8")
    print("Original Message:", message.decode("utf-8"))

    # Hash the message (SHA-256) and convert to integer
    message_hash_int = int_from_hash(message, "sha256")
    print("SHA-256 digest (hex):", hashlib.sha256(message).hexdigest())

    if message_hash_int >= n:
        print("Warning: hash integer >= n. This toy implementation requires n larger than the hash.")
        print("Use larger primes for p and q in this demo, or use proper padding (see notes).")
        # we continue for demo, but this is not secure

    # Signature = hash^d mod n
    signature_int = pow(message_hash_int, d, n)
    signature_hex = hex_from_int(signature_int)
    print("Generated Signature (hex):", signature_hex)

    # Verification: sig^e mod n should equal message_hash_int % n
    verified_hash_int = pow(signature_int, e, n)
    print("\nVerifying Signature...")
    if verified_hash_int % n == message_hash_int % n:
        print("Signature Verified! (Hash matches modulo n)")
    else:
        print("Signature Invalid!")

if __name__ == "__main__":
    main()