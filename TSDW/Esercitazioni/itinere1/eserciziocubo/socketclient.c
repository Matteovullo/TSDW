#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <errno.h>

int main(int argc, char**argv)
{ int sockfd,n;
  struct sockaddr_in local_addr, dest_addr;
  char sendline[1000];
  char recvline[1000];

  if (argc != 2)
  { printf("usage:  client IP_address <Port\n");
    return 1;
  }

  sockfd=socket(AF_INET,SOCK_STREAM,0);

  memset( &dest_addr, 0, sizeof(dest_addr));
  dest_addr.sin_family = AF_INET;
  dest_addr.sin_addr.s_addr = inet_addr(argv[1]);
  dest_addr.sin_port = htons(3333);

  connect(sockfd, (struct sockaddr *) &dest_addr, sizeof(dest_addr));

  while (1)
  { 
    printf("Inserisci un numero!\n");
    fgets(sendline,999,stdin);
    send(sockfd,sendline,strlen(sendline),0);
    n=recv(sockfd,recvline,999,0);
    recvline[n]=0;
    printf("%s", recvline);
   }
}