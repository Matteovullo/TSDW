#include<stdio.h>
#include<stdlib.h>
#include<arpa/inet.h>
#include<string.h>
#include<unistd.h>
#include<errno.h>

int main(int argc, char* argv[]){

    struct sockaddr_in dest_addr;
    socklen_t len=sizeof(struct sockaddr_in);
    int s;
    char buffer[1000];

    if(argc<2){
        printf("Inserire un indirizzo e una porta!");
    }

    struct hostent * host;

    memset(&dest_addr, 0, len);
    dest_addr.sin_port=htons(atoi(argv[1]));
    dest_addr.sin_family=AF_INET;
    inet_pton(AF_INET, argv[1], &(dest_addr.sin_addr));
    //if ((host = gethostbyname(hostname_or_ip)) == NULL) {
    //    herror("querying");
    //    return(-1);             
    //}

    if((s=socket(AF_INET, SOCK_DGRAM, 0)) == -1){
        printf("Errore nell'apertura della socket!");
    }

    while(1){
        read(s, buffer, 1000);
    }

    close(s);






    
    
}