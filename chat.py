import socket
from threading import Thread
import sys



s = socket.socket()


PORTA = int(sys.argv[1] if len(sys.argv) > 1 else 8000 )

s.bind(('localhost', PORTA))
s.listen()

conexoes = []


def enviaMensagem(msg, conexao, cliente):
    for c in conexoes:
        if (c != conexao):
            nome = "%s :> " %cliente
            c.sendall(nome.encode('utf-8'))
            c.sendall(msg)


def msgSaida(cliente):
    msg_saida = "%s saiu da conversa\n" % cliente
    for conexao in conexoes:
            conexao.sendall(msg_saida.encode('utf-8'))


def Cliente(cliente, conexao):
    while True:
        msg = conexao.recv(4096)
        if (msg == b':bye\n'):
            conexoes.remove(conexao)
            msgSaida(cliente)
            conexao.close()
            break
        elif (msg != ""):
            enviaMensagem(msg, conexao, cliente)


def escutaConexao():
    while True:
        conexao, cliente = s.accept()
        conexoes.append(conexao)

        conexao.sendall("Escolha seu nome de usuário: ".encode('utf-8'))
        nick = conexao.recv(4096)
        nick_decod = nick.decode('utf-8').split("\n")[0]

        print("Cliente %s conectado" % nick_decod)
        if (cliente):
            thread = Thread(target=Cliente, args=(nick_decod, conexao)).start()


Thread(target=escutaConexao).start()
