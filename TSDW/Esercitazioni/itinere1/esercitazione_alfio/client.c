#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<sys/socket.h>
#include<arpa/inet.h>
#include<unistd.h>

void handle(char* r){
    printf("%s\n", r);
    exit(-1);
}

int main(int argc, char** argv){

    struct sockaddr_in server_addr;
    socklen_t len=sizeof(struct sockaddr);

    char sendline[1000];
    char recvline[1000];

    int sockfd, n;

    if(argc<2) handle("argc");

    if((sockfd=socket(AF_INET, SOCK_STREAM, 0))<0) handle("socket");

    memset(&server_addr, 0, len);
    server_addr.sin_addr.s_addr=inet_addr(argv[1]);
    server_addr.sin_port=htons(8888);
    server_addr.sin_family=AF_INET;

    if(connect(sockfd, (struct sockaddr*)&server_addr, len)<0) handle("connect");

    while(1){
        printf("Inserire una richesta\n");
        fgets(sendline, 999, stdin);
        if(send(sockfd, sendline, strlen(sendline), 0)<0) handle("send");
        if((n=recv(sockfd, recvline, 999, 0))<0) handle("recv");
        recvline[n]=0;
        printf("%s\n", recvline);
    }

    close(sockfd);
    exit(0);
}