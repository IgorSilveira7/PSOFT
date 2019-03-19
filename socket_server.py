import socket

HOST = '127.0.0.1'
PORT = 5000

tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

origem = (HOST, PORT)

tcp.bind(origem)
tcp.listen(1)

while True:
    connection, client = tcp.accept()
    print 'Connected by ', client
    
    while True:
        msg = connection.recv(1024)
        
        if not msg:
            break
        
        print client, msg
    
    print 'Finalizing client connection', client
    connection.close()


