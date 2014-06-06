public class CoolClient {

    public static void main(String args[]) {

      try {

              MyCoolClass p = new CoolClass_Stub();
              p.setDevice("laptop", "Lenovo");
              System.out.println(p.getDevice(args[0]));
      }
      catch(Throwable t) {
          t.printStackTrace();
          System.exit(0);
      }
    }
}
