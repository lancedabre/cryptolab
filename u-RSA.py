#key generation.
from Crypto.PublicKey import RSA

key = RSA.generate(2048)
pem = key.export_key(format='PEM', passphrase='dees')
f= open('u-RSA.pem', 'wb')
f.write(pem)
f.close()

pub_pem = key.publickey().export_key(format='PEM')
f= open('u-RSA.pub', 'wb')
f.write(pub_pem)
f.close()
