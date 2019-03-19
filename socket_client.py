import socket

HOST = '127.0.0.1'
PORT = 5000

tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

destino = (HOST, PORT)

tcp.connect(destino)

msg = raw_input('Type here...\n')

while msg != '':
    tcp.send(msg)
    msg = raw_input()

tcp.close()


