import socket
import sys

s = socket.socket()

porta = int(sys.argv[1] if len(sys.argv) > 1 else 9090)

s.bind(('localhost', porta))
s.listen()

conexao, cliente = s.accept()

def check_errors(verb, resource, protocol):
    msg_error = ''
    if(verb != 'GET'):
        msg_error = 'Error 405: Method Not Allowed'
    elif(resource != '/'):
        msg_error = 'Error 404: Not Found'
    

    if(msg_error != ''):
        return msg_error
    else:
        return '200 OK'



def parse_request(msg):
    request = msg.split('\\n\\n')
    requestLine = request[0].split('\\n')[0]
    headerLine = request[0].split('\\n')
    headerLine.pop(0)

    verb = requestLine.split()[0]
    resource = requestLine.split()[1]
    protocol = requestLine.split()[2]

    headers = {}

    for h in headerLine :
        header = h.split(':')
        chave = header[0]
        valor = header[1]
        headers[chave] = valor
    
    result = check_errors(verb, resource, protocol)
    response = 'HTTP/1.1 ' + result + '\n'
    response_body = ''
    if(result == '200 OK'):
        response_body = "Este é o conteúdo do recurso '/' neste servidor."
        response_header = "Content-type: text/html; charset=utf-8"
        response += response_body + '\n' + response_header + '\n'


    return response


while True:
    msg = conexao.recv(4096)
    if(msg != ''):
        response = parse_request(msg.decode('utf-8'))
        conexao.sendall(response.encode('utf-8'))
    
