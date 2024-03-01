/* sndrcvConnClntStream.c */

/* usa ·send()/recv() con le socket stream, la comunicazione
 * e` connection-oriented in realta`; qui la semantica e` la stessa di inclnt.c
 */

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>

#include <sys/socket.h>
#include <netdb.h>
#include <netinet/in.h>
#include <arpa/inet.h>

#define MAXBUF 1024
#define SERVERPORT 3002
#define SERVERNAME "localhost"

int mkaddr(struct sockaddr_in *, const char *, u_int16_t);


int main(int argc, char * argv[])
{
	int client_socket, retcode, s_addr_len;
	struct sockaddr_in server_addr, client_addr;
	char msg[MAXBUF];

	// apertura del socket del client
	client_socket = socket(AF_INET, SOCK_STREAM, 0);
	if (client_socket == -1)
		{perror("opening client socket"); exit(-1);}

	// preparazione indirizzo locale (client) per il socket
	// non e`utile per le socket stream, anzi causa EADDRINUSE
	// se non e`trascorso un timeout
	mkaddr(&client_addr, "localhost", 55000);
	retcode =
	bind(client_socket,
	     (struct sockaddr *) &client_addr, //NB: type cast
	     sizeof(client_addr)
	);
	if(retcode == -1)
		{perror("binding socket for client"); exit(-1);}
	// preparazione indirizzo locale (client) facoltativa

	// preparazione indirizzo del server remoto
	mkaddr(&server_addr, SERVERNAME, SERVERPORT);

	retcode = connect(client_socket, 	         // non in sndrcvClessClnt.c
	        (struct sockaddr *) &server_addr,
	        s_addr_len = sizeof(server_addr) );
	if (retcode == -1)
		{perror("connecting socket"); exit(-1);}

////
	// invio del messaggio al server
	strcpy(msg, argc > 1 ? argv[1] : "<>");		// msg must be a string
	retcode = send( client_socket, msg, strlen(msg)+1, 0);
	if (retcode == -1)
		{perror("sending"); exit(-1); }

	// should not overwrite server_addr here (clessclnt.c does)
//	memset(&server_addr, 0, s_addr_len);

	// riceve risposta dal server (servirebbe un while()...)
	retcode = recv( client_socket, msg, strlen(msg)+1, 0);
	if (retcode == -1)
		{perror("receiving"); exit(-1); }
	msg[retcode] = 0;	// msg must be a string
	printf( "\nServer %s replies %d bytes:\n%s\n\n",
	        inet_ntoa(server_addr.sin_addr),
	        retcode, msg );

	close(client_socket);
	exit(0);
}
