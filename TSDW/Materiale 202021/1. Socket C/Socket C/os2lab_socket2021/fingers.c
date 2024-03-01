/* fingers.c */

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>     // per inet_aton(), ...

#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define LOCIP "127.0.0.1"  // per ascoltare *ogni* interfaccia locale di
//#define LOCIP "0.0.0.0"  // rete, usare "0.0.0.0" -> INADDR_ANY (0x0)

#define MYFINGERPORT 2000  // N.B.: numero di port *in formato host*

#define MAXQ 512
#define MAXBUF 1024
#define NITER 10
#define MANYBYTES 16*1024
#define DELAY 7


int main()
{
    int s, s1;
    struct sockaddr_in locAddr, farAddr;
    socklen_t farAddrL, ipAddrL;
    int iter, retcode;
    char buf[MAXBUF], msg[MAXBUF], outbuf[MANYBYTES], *p, *q;
    int mkaddr(struct sockaddr_in *, char * ipaddr, u_int16_t port);

    // Creazione socket
    if ( (s = socket(PF_INET, SOCK_STREAM, 0)) == -1 )
        {perror("creating socket"); exit(-10);}

    mkaddr(&locAddr, LOCIP, // pone nella struct sockaddr_in locAddr, indirizzo
           MYFINGERPORT);   // formato da IP LOCIP e port MYFINGERPORT

    ipAddrL = farAddrL = sizeof(struct sockaddr_in);
    if ( bind(s, (struct sockaddr *) &locAddr, ipAddrL) == -1 )
        {perror("trying to bind"); exit(-1);}
    listen(s, MAXQ);
    printf("Server pronto su port %d\n", MYFINGERPORT);
////
    while ((s1 =
        accept(s, (struct sockaddr *) &farAddr, &farAddrL)
        ) != -1) {
        printf("Client from %s/%d connected, its address is %d bytes long\n",
        inet_ntoa(farAddr.sin_addr),
        ntohs(farAddr.sin_port), farAddrL);

        // NB: NON E' CORRETTO un solo read() qui, ma basta se il cliente
        if ( (retcode = read(s1,buf,MAXBUF)) > 0) // manda solo pochi char
        {
            printf("Il client dice: \n<\n");    // scrivi su standard output, ovvero
            write(STDOUT_FILENO, buf, retcode); // scrive buf su STDOUT_FILENO anche
            printf(">\n");                      // se buf non e`una stringa!
        }

        // reply to client, send various data
        sprintf(msg, "Sono il server: mandero` %d byte\n", //msg diventa cosi`
                retcode*NITER);                            // una stringa
        write(s1, msg, strlen(msg));               // invia msg al client
        sleep(DELAY);
        for (iter = 0; iter < NITER; iter++)  // questi NITER write() saranno
            write(s1,buf,retcode);            // letti con n.?? read() del client

        sleep(1);   // con questo sleep(), il client usera` una read() solo per
                    // leggere il prossimo messaggio
        sprintf(msg, "Sono il server: mandero` %d byte\n", MANYBYTES);
        write(s1, msg, strlen(msg));    // msg e` di certo una stringa
        for (p = outbuf, q = p+MANYBYTES; p < q; p++)  // riempie outbuf
            *p = 'X';                                  // di 'X'
        sleep(DELAY);
        write(s1, outbuf, MANYBYTES);
        close(s1);
        printf("Connection with client closed\n\n");
    } // exit while (solo se accept() torna con -1)

    // Server non termina; servirebbe gestore SIGINT
    exit(0);
}

