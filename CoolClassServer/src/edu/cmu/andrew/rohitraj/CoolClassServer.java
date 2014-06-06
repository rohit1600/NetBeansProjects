public class CoolClassServer {

    public static void main(String args[]) {

           System.out.println("Main");
           MyCool_Skeleton cs =
                                new MyCool_Skeleton(new MyCoolClass_Servant());
           cs.serve();

    }
}
