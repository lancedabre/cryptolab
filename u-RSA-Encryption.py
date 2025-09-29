from Crypto.Cipher import PKCS1_OAEP
from Crypto.PublicKey import RSA

message = B'A secret message!\n'

key = RSA.import_key(open('u-RSA.pub').read())
cipher = PKCS1_OAEP.new(key)
ciphertext = cipher.encrypt(message)
f= open('u-RSA-encrypted.txt', 'wb')
f.write(ciphertext)
f.close()
