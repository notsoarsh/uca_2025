public class SingleTon {
  
  private Integer mutex = 10;  
  private static volatile SingleTon instance = null;
    
  private SingleTon() {
    //no use
    //say obj creation takes 2ms
    Thread.sleep(2);
  }

 public static SingleTon getInstance() {
    
    if (instance == null) {
     synchronized(mutex) { //only synchronize when u have null reduces wait time for others
       if (instance == null)  instance = new SingleTon();
     }
    }
    return instance;
  }
}

//we put a double check as one thread might have created and still if we allow creation of another one the class is no longer singleton
