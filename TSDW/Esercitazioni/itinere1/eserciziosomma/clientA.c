#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#include<unistd.h>
#include<arpa/inet.h>

int main(int argc, char* argv[]){

    struct sockaddr_in dest_addr;
    socklen_t len=sizeof(struct sockaddr_in);
    int sockfd, n;
    char sendline[1000];
    char recvline[1000];

    if(argc < 1){
        printf("Inserire un indirizzo!");
    }

    if((sockfd=socket(AF_INET, SOCK_STREAM, 0))<0){
        printf("Errore nella socket!");
    }

    memset(&dest_addr, 0, len);
    dest_addr.sin_family=AF_INET;
    dest_addr.sin_port=htons(3333);
    dest_addr.sin_addr.s_addr=inet_addr(argv[1]);

    connect(sockfd, (struct sockaddr*)&dest_addr, len);

        printf("Scrivi qualcosa:\n");
        fgets(sendline, 999, stdin);
        send(sockfd, sendline, strlen(sendline), 0);
        n=recv(sockfd, recvline, 999, 0);
        recvline[n]=0;
        printf("%s", recvline);

    close(sockfd);
    exit(1);
}