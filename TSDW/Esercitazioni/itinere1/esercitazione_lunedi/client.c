#include<stdlib.h>
#include<stdio.h>
#include<arpa/inet.h>
#include<sys/socket.h>
#include<string.h>
#include<unistd.h>

void handle(char* richiesta){
    printf("%s\n", richiesta);
    exit(-1);
}

int main(int argc, char** argv){

    struct sockaddr_in server_addr;
    socklen_t len=sizeof(struct sockaddr);
    int sockfd, n;
    char sendline[1000];
    char recvline[1000];

    if(argc<2) handle("argc");

    if((sockfd=socket(AF_INET, SOCK_STREAM, 0))<0) handle("socket");

    memset(&server_addr, 0, len);
    server_addr.sin_family=AF_INET;
    server_addr.sin_addr.s_addr=inet_addr(argv[1]);
    server_addr.sin_port=htons(5533);

    if(connect(sockfd, (struct sockaddr*)&server_addr, len)<0) handle("connect");

    while(1){
        printf("Inserire una stringa\n");
        fgets(sendline, 999, stdin);
        if(send(sockfd, sendline, strlen(sendline), 0)<0) handle("send");
        if((n=recv(sockfd, recvline, 999, 0))<0) handle("recv");
        recvline[n]=0;
        printf("%s\n", recvline);
    }

    close(sockfd);
    return 0;
}