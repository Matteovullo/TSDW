#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <errno.h>

int main(int argc, char** argv) {
    int sockfd, n;
    struct sockaddr_in dest_addr;
    socklen_t len = sizeof(struct sockaddr_in);
    char sendline[1000];
    char recvline[1000];

    if (argc < 2) {
        printf("Inserisci un indirizzo IP\n");
        return 1;
    }

    sockfd = socket(AF_INET, SOCK_STREAM, 0);

    memset(&dest_addr, 0, len);
    dest_addr.sin_port = htons(7777);
    dest_addr.sin_family = AF_INET;
    dest_addr.sin_addr.s_addr = inet_addr(argv[1]);

    connect(sockfd, (struct sockaddr*) &dest_addr, sizeof(dest_addr));

    while (1) {
        printf("Inserisci LIST oppure una stringa\n");
        fgets(sendline, 999, stdin);
        send(sockfd, sendline, strlen(sendline), 0);
        
        n = recv(sockfd, recvline, 999, 0);
        if (n > 0) {
            recvline[n] = 0;
            printf("Server response: %s\n", recvline);
        }
    }
    return 0;
}
