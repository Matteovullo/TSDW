public class OffertaAbbonati {
    public static String[] serietv = { "CSI", "Game of thrones", "Friends", "Breaking bad", "Mr Robot", "Jack ryan" };
    public static int[] episodi = { 140, 80, 200, 80, 45, 40 };

    public static int getDisponibilita(String titolo, int episodio){
    for(int i = 0; i < 6; i++)
            if ( serietv[i].equals(titolo) )
                return (episodio <= episodi[i]) ? 1 : 0;
        return -1;
    }
}