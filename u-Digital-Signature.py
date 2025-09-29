#using RSA-PSS

from Crypto.Signature import pss
from Crypto.Hash import SHA256
from Crypto.PublicKey import RSA

message = b'A secret message!\n'
key_pem = open('private.pem').read()
key= RSA.import_key(key_pem, passphrase='dees')
h= SHA256.new(message)
signer = pss.new(key)
signature = signer.sign(h)
f= open('u-RSA-signature.txt', 'wb').write(signature)
