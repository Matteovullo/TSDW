#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<unistd.h>
#include<arpa/inet.h>
#include<sys/socket.h>
#include<string.h>

void handle(char* s){
    printf("%s\n", s);
    exit(-1);
}

int main(){
    struct sockaddr_in server_addr;
    socklen_t len=sizeof(struct sockaddr);
    char sendline[1000], recvline[1000];
    int sockfd, n;

    if((sockfd=socket(AF_INET, SOCK_STREAM, 0))<0) handle("socket");

    memset(&server_addr, 0, len);
    server_addr.sin_addr.s_addr=inet_addr("127.0.0.1");
    server_addr.sin_port=htons(3333);
    server_addr.sin_family=AF_INET;

    if(connect(sockfd, (struct sockaddr*)&server_addr, len)<0) handle("connect");

    //while(1){
        printf("Inserisci una stringa:\n");
        fgets(sendline, 999, stdin);
        if(send(sockfd, sendline, strlen(sendline), 0)<0) handle("send");
        if((n=recv(sockfd, recvline, 999, 0))<0) handle("recv");
        recvline[n]=0;
        printf("%s", recvline);
    //}

    close(sockfd);
    exit(0);
}