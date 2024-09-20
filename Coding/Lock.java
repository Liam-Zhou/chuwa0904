package Coding;

class Employer {
    public void say(){
        System.out.println("You need work experience to get a job");
    }
    public void get(){
        System.out.println("Employer hires the employee");
    }
}

class Employee {
    public void say(){
        System.out.println("You need a job to get work experience");
    }
    public void get(){
        System.out.println("Employee get a job");
    }
}

class Test implements Runnable{
    private Employer employer = new Employer();
    private Employee employee = new Employee();

    private boolean isEmployer = true;
    
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();
    @Override
    public void run(){
        //* Deadlock example */
        // if(isEmployer){
        //     synchronized (lock1){
        //         employer.say();
        //         synchronized(lock2){ //Employer needs to get lock 2 to proceed
        //             employer.get();
        //         }
        //     }
        // }else{
        //     synchronized(lock2){
        //         employee.say();
        //         synchronized(lock1){// Employee needs to get lock 1 from employer to proceed
        //             employee.get();
        //         }
        //     }
        // }

        // in this version their is good lock order. So no deadlock
        if(isEmployer){
            synchronized (lock1){
                employer.say();
                synchronized(lock2){ //Employer needs to get lock 2 to proceed
                    employer.get();
                }
            }
        }else{
            synchronized(lock1){
                employee.say();
                synchronized(lock2){// Employee needs to get lock 1 from employer to proceed
                    employee.get();
                }
            }
        }

    }
    public static void main(String[] args) {
        Test t1 = new Test();
        t1.isEmployer = true;
        new Thread(t1,"employer thread").start();
        Test t2 = new Test();
        t2.isEmployer = false;
        new Thread(t2,"employee thread").start();
    }
}
