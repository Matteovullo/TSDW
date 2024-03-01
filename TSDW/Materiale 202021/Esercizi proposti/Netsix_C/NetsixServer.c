#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <netdb.h>
#include <ctype.h>

#include "OffertaAbbonati.h"

#define PORT 39999
#define MAXBUF 2048

void makeAnswer(char *, char *, int);
int checkString(char*);

int main(int argc, char** argv){

    int welcomeSock, connectionSock, retcode, check = 1;
    struct sockaddr_in server, client;
    socklen_t clientLen;
    char msg[MAXBUF];
    char answer[50];
    char * titolo, * episodio_str;
    int episodio;

    if((welcomeSock = socket(AF_INET, SOCK_STREAM, 0)) < 0){
        perror("socket()\n");
        exit(-1);
    }

    if((setsockopt(welcomeSock, SOL_SOCKET, SO_REUSEADDR, &check, sizeof(check))) < 0){
        perror("setsockopt()\n");
        exit(-1);
    }

    memset(&server, 0, sizeof(server));
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(PORT);

    if((bind(welcomeSock, (struct sockaddr *)&server, sizeof(server))) < 0){
        perror("bind()\n");
        exit(-1);
    }

    printf("---Server ready---\n");

    listen(welcomeSock, 1);

    clientLen = sizeof(client);

    while (1){

        if((connectionSock = accept(welcomeSock, (struct sockaddr *)&client, &clientLen)) < 0){
            perror("connection()\n");
            exit(-1);
        }
        printf("Connection established: [%s, %d]\n", 
               inet_ntoa(client.sin_addr), ntohs(client.sin_port));
        if ( (retcode = read(connectionSock, &msg, MAXBUF)) < 0 ) {
            perror("read()\n");
            exit(-1);
        }
        msg[retcode] = '\0';
        memset(answer, '0', sizeof(answer));

        titolo = strtok(msg, ",");
        episodio_str = strtok(NULL, ",");
        if ( (episodio = atoi(episodio_str)) == 0) {
            sprintf(answer, "\"%s\" non e` un n. di episodio\n", episodio_str);
            printf("%s", answer);
        } else {
            printf("---Client request---\nTitle: %s\tEpisode:%d\n", titolo, episodio);
            makeAnswer(answer, titolo, episodio);
        }
        if( (retcode = write(connectionSock, &answer, strlen(answer))) < 0 ) {
            perror("write()\n");
            exit(-1);
        }
        printf("---Sent %d bytes---\n", retcode);
        close(connectionSock);  
    }
    close(welcomeSock);
}

void makeAnswer(char *msg, char* title, int episode){
    switch(getDisponibilita(title, episode)){
        case 0 :
            sprintf(msg, "Episodio %d Coming Soon", episode);
            break;
        case 1 :
            sprintf(msg, "Episodio %d disponibile", episode);
            break;
        default:
            sprintf(msg, "\"%s\" non in catalogo", title);
    }
}
