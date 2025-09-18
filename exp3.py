# Diffie-Hellman Key Exchange Algorithm

# Public keys
P = int(input("enter the value of P: "))
G = int(input("enter the value of G: "))

# Private keys
a = int(input("enter the value of A: "))
b = int(input("enter the value of B: "))

# Calculate public keys
x = pow(G, a, P) # Alice's public key: G^a mod P
y = pow(G, b, P) # Bob's public key: G^b mod P

# Calculate shared secret keys
ka = pow(y, a, P) # Alice's secret key
kb = pow(x, b, P) # Bob's secret key

# Print results
print(f"Public Keys: P = {P}, G = {G}")
print(f"Alice's private key: a = {a}")
print(f"Bob's private key: b = {b}")
print(f"Alice's public key: x = {x}")
print(f"Bob's public key: y = {y}")

print(f"Alice's secret key: ka = {ka}")
print(f"Bob's secret key: kb = {kb}")

# Verify shared secret keys match
if ka == kb:
    print(f"Shared secret key established: {ka}")
else:
    print("Error: Shared secret keys do not match!")
