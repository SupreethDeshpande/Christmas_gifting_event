import java.sql.*;
import java.io.*;
import java.util.*;
class NoRecordException extends Exception
        {
            
            String str1;
            NoRecordException(String str2)
            {
                str1=str2;
            }
            public String toString()
            {
                return ("NoRecordException: "+str1);
            }
        }
public class Christmas {
    static boolean signal1,signal2,signal3;
    static int win,x1,x2,i1,i2,max=50;
    static int day=1;
    static int min=10;
    static int index=0;
    static int count=1;
    static String shared_through="";
    static String control="";
    static String rndm[]=new String[5];
    static String arr[]={"TREE","BULB","CANDY_STICK","BELL","STAR"};
     static Map<Integer,Integer> c=new HashMap<Integer,Integer>();
     static Map<Integer,Integer> d=new HashMap<Integer,Integer>();
     static Map<Integer,Integer> e=new HashMap<Integer,Integer>();
     static Map<Integer,Integer> w=new HashMap<Integer,Integer>();
     public static void update_gift(int a,String s_gift,String r_gift)
            {
               Connection con=null;
               Statement stmt=null;
               ResultSet rs,rs1,rs2;
               rs=rs1=rs2=null;
                try
                {
                   
                        
                    Class.forName("oracle.jdbc.driver.OracleDriver");
         
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","welcome");
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            rs1=stmt.executeQuery("select TREE,BULB,CANDY_STICK,BELL,STAR from GIFT where ID='"+a+"'");
            if(!(rs1.next()))
            {
                rs2=stmt.executeQuery("insert into GIFT values('"+a+"',0,0,0,0,0)");
               
            }
             rs=stmt.executeQuery("select TREE,BULB,CANDY_STICK,BELL,STAR from GIFT where ID='"+a+"'");
            while(rs.next())
            {
           
            if(s_gift=="")
            {
                
                rs.updateInt(r_gift,rs.getInt(r_gift)+1);
                rs.updateRow();
            }
             if(s_gift!="")
            {
                rs.updateInt(s_gift,rs.getInt(s_gift)-1);
                
                rs.updateInt(r_gift,rs.getInt(r_gift)+1);
                rs.updateRow();
                return;
            }
            
            }
            
            
                }
                catch(SQLIntegrityConstraintViolationException e)
     {
         
             System.out.println("Primary Key Violation in Update_Gift");
         
     }
                
                catch(Exception e){
                    System.out.println(e);
                    }
                finally
                {
                    try{rs.close();} catch (Exception e){}
                    try{rs1.close();} catch (Exception e){}
                    try{rs2.close();} catch (Exception e){}
                    try{stmt.close();} catch (Exception e){}
                    try{con.close();} catch (Exception e){}
                }
            
            }
     
     public  static void update_act(int a)
    {
           Connection con=null;
               Statement stmt=null;
               ResultSet rs,rs1,rs2;
               rs=rs1=rs2=null;
         
               try  {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
         
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","welcome");
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs1=stmt.executeQuery("select daily,sharing,transaction,points from activity where id='"+a+"'");
            if(!(rs1.next()))
            {
                 rs2=stmt.executeQuery("insert into activity values('"+a+"',0,0,0,0)");
                 
            }
             rs=stmt.executeQuery("select id,daily,sharing,transaction,points from activity where id='"+a+"'");
             while(rs.next())
             {
                 rs.updateInt(shared_through,rs.getInt(shared_through)+1);
                 rs.updateInt("points",rs.getInt("points")+1);
                 rs.updateRow();
             }
             
             
            
                }
   catch(SQLIntegrityConstraintViolationException e)
     {
         
             System.out.println("Primary Key Violation in Update_Activity");
         
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
               finally
                {
                    try{rs.close();} catch (Exception e){}
                    try{rs1.close();} catch (Exception e){}
                    try{rs2.close();} catch (Exception e){}
                    try{stmt.close();} catch (Exception e){}
                    try{con.close();} catch (Exception e){}
                }
    }
      
      public static String get_name(int a)
    {
        String name="";
        Connection con=null;
               Statement stmt=null;
               ResultSet rs,rs1;
               rs=rs1=null;
        try
                {
                   
                        
                    Class.forName("oracle.jdbc.driver.OracleDriver");
         
             con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","welcome");
             stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             rs1=stmt.executeQuery("select name from users where id='"+a+"' ");
            if(rs1.next())
            {
                 rs=stmt.executeQuery("select name from users where id='"+a+"' ");
            while(rs.next())
            {
                name=rs.getString(1);
               
            }
             }
            else
            {
                throw new NoRecordException ("Record Not Found in get_name");
            }
            
            
            
                }
        catch(NoRecordException e)
        {
            System.out.println(e);
        }
         catch(SQLIntegrityConstraintViolationException e)
     {
         
             System.out.println("Primary Key Violation in Transaction");
         
     }
        catch(Exception e)
     {
         System.out.println(e);
     }
        finally
                {
                    try{rs.close();} catch (Exception e){}
                    try{rs1.close();} catch (Exception e){}
                    
                    try{stmt.close();} catch (Exception e){}
                    try{con.close();} catch (Exception e){}
                }
        return name;
    }
      
       public static void update_recipient(int a,int b,int pos)
        {
            Connection con=null;
               Statement stmt=null;
               ResultSet rs,rs1;
               rs=rs1=null;
            try
        {
        Class.forName("oracle.jdbc.driver.OracleDriver");
         
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","welcome");
              stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             rs1=stmt.executeQuery("select p1,p2,p3,p4,p5 from recipient where id='"+a+"' ");
            if(rs1.next())
            {
                rs=stmt.executeQuery("select p1,p2,p3,p4,p5 from recipient where id='"+a+"' ");
                while(rs.next())
                {
                    rs.updateInt(pos,b);
                    rs.updateRow();
                }
                
            }
            else
            {
                stmt.executeQuery("insert into recipient values('"+a+"','"+b+"',0,0,0,0)");
            }
            
            
        }
        catch(SQLIntegrityConstraintViolationException e)
     {
         
             System.out.println("Primary Key Violation in Transaction");
         
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
            finally
                {
                    try{rs.close();} catch (Exception e){}
                    try{rs1.close();} catch (Exception e){}
                    try{stmt.close();} catch (Exception e){}
                    try{con.close();} catch (Exception e){}
                }
        }
       public  static void update_transaction(int a,double amt,int b)
    {
        Connection con=null;
               Statement stmt=null;
               
     try
                {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
         
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","welcome");
             stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
         
             stmt.executeQuery("insert into transaction values('"+a+"','"+amt+"','"+b+"')");
                   
                }
        
                
     catch(SQLIntegrityConstraintViolationException e)
     {
         
             System.out.println("Primary Key Violation in update_transaction");
         
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
     finally
     {
         try{stmt.close();} catch (Exception e){}
          try{con.close();} catch (Exception e){}
     }
    }
       public static String rndm_gift(int a)
    {
        
        String x="";
        index=0;
      rndm[0]=rndm[1]=rndm[2]=rndm[3]=rndm[4]="";
      
        Connection con=null;
               Statement stmt=null;
               ResultSet rs=null;
               
        
        try
        {
        Class.forName("oracle.jdbc.driver.OracleDriver");
         
             con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","welcome");
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
           
            for(int i=1;i<=5;i++)
            {
               rs=stmt.executeQuery("select TREE,BULB,CANDY_STICK,BELL,STAR from GIFT where ID='"+a+"' ");
                while(rs.next())
                {
                       if(rs.getInt(i)>0)
                    {
                        rndm[index]=arr[i-1];
                        index++;
                    }
                }
            }
            if(control=="manual")
            {System.out.println("Select a Gift to Share");
            System.out.println(rndm[0]+" "+rndm[1]+" "+rndm[2]+" "+rndm[3]+" "+rndm[4]);
            }
            x=rndm[new Random().nextInt((index))];
            
        }
   catch(SQLIntegrityConstraintViolationException e)
     {
         
             System.out.println("Primary Key Violation in Random_Gift");
         
     }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
                {
                    try{rs.close();} catch (Exception e){}
                    try{stmt.close();} catch (Exception e){}
                    try{con.close();} catch (Exception e){}
                }
        return x;
    }
       public static String get_sharing_gift(int a)
        {
            Connection con=null;
               Statement stmt=null;
               ResultSet rs1,rs2;
               rs1=rs2=null;
            try
            {
            Class.forName("oracle.jdbc.driver.OracleDriver");
             con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","welcome");
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            if((day==5)||(day==6))
            {
                 rs1=stmt.executeQuery("select id,points from (select * from(select * from activity order by points desc) where rownum<=30) where id='"+a+"' ");
                if(rs1.next())
                {
                     rs2=stmt.executeQuery("select TREE,BULB,CANDY_STICK,BELL,STAR from GIFT where ID='"+a+"'");
                    while(rs2.next())
                    {
                        if((rs2.getInt(1)>0)&&(rs2.getInt(2)>0)&&(rs2.getInt(3)>0))
                        {
                           
                            return arr[3];
                        }
                    }
                }
                
            }
            
           }
            catch(Exception e)
            {
                System.out.println(e);
            }
            finally
                {
                    try{rs1.close();} catch (Exception e){}
                    try{rs2.close();} catch (Exception e){}
                    try{stmt.close();} catch (Exception e){}
                    try{con.close();} catch (Exception e){}
                }
            return (rndm[new Random().nextInt(index)]);
        }
       
       public static String get_transaction_gift(int a,double amt)
        {
             Connection con=null;
               Statement stmt=null;
               ResultSet rs,rs1,rs2;
               CallableStatement st=null;
               rs=rs1=rs2=null;
            try
            {
                Class.forName("oracle.jdbc.driver.OracleDriver");
         
             con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","welcome");
             st=con.prepareCall("{call update_winners(?,?,?,?)}");
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            if((day==7)&&(!w.containsKey(a)))
            {
                     rs2=stmt.executeQuery("select TREE,BULB,CANDY_STICK,BELL,STAR from GIFT where ID='"+a+"'");
                    while(rs2.next())
                    {
                        if((amt>=200.0)&&((rs2.getInt(1)>0)&&(rs2.getInt(2)>0)&&(rs2.getInt(3)>0)&&(rs2.getInt(4)>0)))
                        {
                            st.setInt(1,count);
                            st.setInt(2,a);
                            st.setString(3,Christmas.get_name(a));
                             st.setDouble(4,101+Math.round(400*(new Random().nextDouble())));
                             st.execute();
                             w.put(a,1);
                             count++;
                            return arr[4];
                           
                        }
                    }
            }
            if((day==5)||(day==6)||(day==7))
            {
                 rs1=stmt.executeQuery("select id,points from (select * from(select * from activity order by points desc) where rownum<=30) where id='"+a+"' ");
                
                if(rs1.next())
                {
                     rs=stmt.executeQuery("select TREE,BULB,CANDY_STICK,BELL,STAR from GIFT where ID='"+a+"'");
                    while(rs.next())
                    {
                        if((rs.getInt(1)>0)&&(rs.getInt(2)>0)&&(rs.getInt(3)>0))
                        {
                            return arr[3];
                        }
                    }
                }
            }
            
            }
            catch(SQLIntegrityConstraintViolationException e)
     {
         
             System.out.println("Primary Key Violation in get_transaction_gift");
         
     }
            catch(Exception e)
                    {
                        System.out.println(e);
                    }
            finally
                {
                    try{rs.close();} catch (Exception e){}
                    try{rs1.close();} catch (Exception e){}
                    try{rs2.close();} catch (Exception e){}
                    try{st.close();} catch (Exception e){}
                    try{stmt.close();} catch (Exception e){}
                    try{con.close();} catch (Exception e){}
                }
            return (arr[new Random().nextInt(3)]);
        }
       public static int add_user(String name,String ph_no,String email_id,String loc,double acc_bal)
       {
           Connection con=null;
               Statement stmt=null;
               ResultSet rs=null;
               int max1=0;
           try
           {
              Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","welcome");
             stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                    rs=stmt.executeQuery("select * from users");
                    while(rs.next())
                    {
                         max1=rs.getInt(1);
                    }
                    max1++;
                    stmt.executeQuery("insert into users values('"+max1+"','"+name+"','"+ph_no+"','"+email_id+"','"+loc+"','"+acc_bal+"')");
                    max++;                    
            }
           catch(SQLIntegrityConstraintViolationException e)
              {
             System.out.println("Primary Key Violation in get_transaction_gift");
              }
            catch(Exception e)
              {
                 System.out.println(e);
              }
           finally
           {
               try{rs.close();} catch (Exception e){}
               try{stmt.close();} catch (Exception e){}
                    try{con.close();} catch (Exception e){}
           }
           return max1;
       }
       public static void delete_user(int a)
       {
           Connection con=null;
               Statement stmt=null;
               ResultSet rs0=null;
           try
           {
           Class.forName("oracle.jdbc.driver.OracleDriver");
             con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","welcome");
             stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             rs0=stmt.executeQuery("select id,name from users where id='"+a+"' ");
                    
                   if(rs0.next())
                   {
                       String s=Christmas.get_name(a);
                       stmt.executeQuery("delete from users where id='"+a+"'");
                       System.out.println(s+"("+a+"):User Removed");
                   }
                   else
                   {
                       System.out.println("User isn't Participating to Remove");
                   }
                      
           }
          
            catch(Exception e)
              {
                 System.out.println(e);
              }
           finally
           {
               try{rs0.close();} catch (Exception e){}
               try{stmt.close();} catch (Exception e){}
                    try{con.close();} catch (Exception e){}
           }
       }
       public static void display_winners()
   {
       
       try
                {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
         
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","welcome");
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            System.out.println("𝗕𝗼𝗮𝗿𝗱 𝗼𝗳 𝗪𝗶𝗻𝗻𝗲𝗿𝘀");
            ResultSet rs=stmt.executeQuery("select * from reward order by sl_no");
            while(rs.next())
            {
                System.out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getString(3)+" "+rs.getDouble(4));
            }
    }
       catch(Exception e){
                    System.out.println(e);
                    }
   }
       public static void auto_daily()
        {
             String s_gift,r_gift;
             int a;
             
             c.clear();
             Connection con=null;
               Statement stmt1=null;
               ResultSet rs0=null;
        try
         {
             shared_through="DAILY";
             
              Class.forName("oracle.jdbc.driver.OracleDriver");
              Random r=new Random();
             con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","welcome");
             stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                stmt1.executeQuery("delete recipient");
   
            for(int i=1;i<=30;i++)
            {
                a=min+(r.nextInt(max));
                
                label3:
                {
                 rs0=stmt1.executeQuery("select id,name from users where id='"+a+"' ");
                   if(rs0.next())
                   {
            if(!(c.containsKey(a)))
            {
                            if(day==1)
                            {
                            r_gift=arr[new Random().nextInt(1)];
                            }
                            else if(day==2)
                            {
                                r_gift=arr[new Random().nextInt(2)];
                            }
                            else
                            {
                                r_gift=arr[new Random().nextInt(3)];
                            }
                           
                            System.out.println(Christmas.get_name(a)+" "+"received "+r_gift+" as daily gift");
                            System.out.println();
                            Christmas.update_gift(a,"",r_gift);
                            Christmas.update_act(a);
                            
                            c.put(a,1);
            }
             else
                        {
                            
                            i=i-1;
                            
                            break label3;
                           
                        }   
            }
           else
                   {
                       i=i-1;
                       
                       break label3;
                   }
            
                }
            }
         
         }
        
        
        
         catch(SQLIntegrityConstraintViolationException e)
     {
         
             System.out.println("Primary Key Violation in Transaction");
         
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
      finally
           {
               try{rs0.close();} catch (Exception e){}
               try{stmt1.close();} catch (Exception e){}
                    try{con.close();} catch (Exception e){}
           }   
        }
       public static int auto_gifts(int a,int b)
        {
             String s_gift,r_gift;
             
             
             Connection con=null;
             Statement stmt0,stmt1,stmt2,stmt3,stmt4;
             ResultSet rs0,rs,rs1,rs2,rs3,rs4;
             stmt0=stmt1=stmt2=stmt3=stmt4=null;rs0=rs=rs1=rs2=rs3=rs4=null;
        try
         {
             shared_through="SHARING";
             
              Class.forName("oracle.jdbc.driver.OracleDriver");
              
             con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","welcome");
             stmt0=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             if(x1==1){
                stmt0.executeQuery("delete recipient");
                c.clear();
             x1=0;}
             stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             stmt2=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             stmt3=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             stmt4=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
                
         
                    rs0=stmt0.executeQuery("select id,name from users where id='"+a+"' ");
                   if(rs0.next())
                   {
                        rs4=stmt0.executeQuery("select id,name from users where id='"+b+"' ");
                       if(rs4.next())
                       {
                     rs1=stmt1.executeQuery("select TREE,BULB,CANDY_STICK,BELL,STAR from GIFT where ID='"+a+"'");
                    if(rs1.next())
                    {   
                      rs3=stmt4.executeQuery("select TREE,BULB,CANDY_STICK,BELL,STAR from GIFT where ID='"+a+"'");
                    while(rs3.next())
                    {      
                    if((a!=b)&&((rs3.getInt(1)>0)||(rs3.getInt(2)>0)||(rs3.getInt(3)>0)||(rs3.getInt(4)>0)||(rs3.getInt(5)>0)))

                    { 
                        
                           if(c.containsKey(a))
                                {
                                     rs2=stmt2.executeQuery("select p1,p2,p3,p4,p5 from recipient where id='"+a+"' ");

                                        if(rs2.next())
                                            {
                                                 rs=stmt3.executeQuery("select p1,p2,p3,p4,p5 from recipient where id='"+a+"' ");
                                                
                                                while(rs.next())
                                                    {

                                                        if((c.get(a)<5)&&((b!=rs.getInt(1))&&(b!=rs.getInt(2))&&(b!=rs.getInt(3))&&(b!=rs.getInt(4))&&(b!=rs.getInt(5))))
                                                            {
                                                                s_gift=Christmas.rndm_gift(a);
                                                                r_gift=Christmas.get_sharing_gift(a);
                                                                System.out.println(Christmas.get_name(a)+" "+"shared "+s_gift+" to "+Christmas.get_name(b));
                                                                System.out.println(Christmas.get_name(a)+" "+"received "+r_gift+" as reward");
                                                                System.out.println();
                                                                Christmas.update_gift(a,s_gift,r_gift);
                                                                Christmas.update_gift(b,"",s_gift);
                                                                Christmas.update_act(a);
                                                                Christmas.update_recipient(a,b,c.get(a)+1);
                                                                c.put(a,c.get(a)+1);
                                                            }
                                                            else
                                                            {
                                                                
                                                                
                                                                
                                                                return 0;
                                                            }
                                                    }
                                                
                                            }
                                            else
                                            {
                                                throw new NoRecordException ("Record Not Found in Recipient");
                                             }

                                }
                                else
                                {
                                    s_gift=Christmas.rndm_gift(a);
                                    r_gift=Christmas.get_sharing_gift(a);
                                    System.out.println(Christmas.get_name(a)+" "+"shared "+s_gift+" to "+Christmas.get_name(b));
                                    System.out.println(Christmas.get_name(a)+" "+"received "+r_gift+" as reward");
                                    System.out.println();
                                    Christmas.update_gift(a,s_gift,r_gift);
                                    Christmas.update_gift(b,"",s_gift);
                                    Christmas.update_act(a);
                                    Christmas.update_recipient(a,b,1);
                                                c.put(a,1);
                                }
                    }else
                    {
                        
                        
                        
                        return 0;
                    }
                    
                    }
                    
          
                
            }
              else
                {
                  
                  
                  
                  return 0;
                }
                   }
                     else
                   {
                       
                  
                  return 0;
                   }   
                   }
                   
                   else
                   {
                       
                  
                  
                  return 0;
                   }
               
               
            
            
            
            }
         
        catch(NoRecordException e)
        {
            System.out.println(e);
        }
         catch(SQLIntegrityConstraintViolationException e)
     {
         
             System.out.println("Primary Key Violation in Transaction");
         
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
        finally
           {
               try{rs0.close();} catch (Exception e){}
               try{rs.close();} catch (Exception e){}
               try{rs1.close();} catch (Exception e){}
               try{rs2.close();} catch (Exception e){}
               try{rs3.close();} catch (Exception e){}
               try{rs4.close();} catch (Exception e){}
               try{stmt0.close();} catch (Exception e){}
               try{stmt1.close();} catch (Exception e){}
               try{stmt2.close();} catch (Exception e){}
               try{stmt3.close();} catch (Exception e){}
               try{stmt4.close();} catch (Exception e){}
                    try{con.close();} catch (Exception e){}
           } 
        return 1;
        }
       public static int auto_transaction(int a,double amt,int b)
        {
             String s_gift,r_gift;
             
             win=1;
             
             double min_amt=50.0;
             
             Connection con=null;
             Statement stmt0,stmt1,stmt2,stmt3;
             ResultSet rs0,rs,rs1,rs2,rs3,rs4;
             stmt0=stmt1=stmt2=stmt3=null;rs0=rs=rs1=rs2=rs3=rs4=null;
        try
         {
             shared_through="TRANSACTION";
             
              Class.forName("oracle.jdbc.driver.OracleDriver");
              Random r=new Random();
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","welcome");
             stmt0=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             if(x2==1){
                stmt0.executeQuery("delete recipient");
                stmt0.executeQuery("delete transaction");
             c.clear();x2=0;}
             stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             stmt2=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            stmt3=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
                
                
                     rs0=stmt0.executeQuery("select id,name from users where id='"+a+"' ");
                   if(rs0.next())
                   {
                        rs3=stmt0.executeQuery("select id,name from users where id='"+b+"' ");
                       if(rs3.next())
                       {
             rs1=stmt1.executeQuery("select acc_bal from users where id='"+a+"' ");
            while(rs1.next())
            {
            if((a!=b)&&((rs1.getInt(1)>amt)))
               
            {
                
             
            if(c.containsKey(a))
            {
                 rs2=stmt2.executeQuery("select p1,p2,p3,p4,p5 from recipient where id='"+a+"' ");
                
                if(rs2.next())
                {
                        
                        
                     rs=stmt3.executeQuery("select p1,p2,p3,p4,p5 from recipient where id='"+a+"' ");
                    while(rs.next())
                    {
                        
                        if((c.get(a)<3)&&((b!=rs.getInt(1))&&(b!=rs.getInt(2))&&(b!=rs.getInt(3))))
                        {
                            
                            r_gift=Christmas.get_transaction_gift(a,amt);
                            System.out.println(Christmas.get_name(a)+" "+"sent ₹"+amt+" to"+Christmas.get_name(b));
                            System.out.println(Christmas.get_name(a)+" "+"received "+r_gift+" as return gift");
                            System.out.println();
                            Christmas.update_gift(a,"",r_gift);
                            Christmas.update_transaction(a,amt,b);
                            Christmas.update_act(a);
                            Christmas.update_recipient(a,b,c.get(a)+1);
                            c.put(a,c.get(a)+1);
                        }
                        else
                        {
                            
                            
                            
                            return 0;
                        }
                    }
                }
                else
                {
                   throw new NoRecordException ("Record Not Found in Recipient()Transaction");
                }
                
            }
            else
            {
                            r_gift=Christmas.get_transaction_gift(a,amt);
                            System.out.println(Christmas.get_name(a)+" "+"sent ₹"+amt+" to "+Christmas.get_name(b));
                            System.out.println(Christmas.get_name(a)+" "+"received "+r_gift+" as return gift");
                            System.out.println();
                            Christmas.update_gift(a,"",r_gift);
                            Christmas.update_transaction(a,amt,b);
                            Christmas.update_act(a);
                            Christmas.update_recipient(a,b,1);
                            c.put(a,1);
            }
         }
            else
            {
                
                
                return 0;
            }
            }
                       }
                       else
                    {
                        
                        
                        return 0;
                        }
                }
                   else
                    {
                       
                        
                        return 0;
                        }
                
                
            
            
         }
        catch(NoRecordException e)
        {
            System.out.println(e);
        }
         catch(SQLIntegrityConstraintViolationException e)
     {
         
             System.out.println("Primary Key Violation in Transaction");
         
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
        finally
           {
               try{rs0.close();} catch (Exception e){}
               try{rs.close();} catch (Exception e){}
               try{rs1.close();} catch (Exception e){}
               try{rs2.close();} catch (Exception e){}
               try{rs3.close();} catch (Exception e){}
               try{rs4.close();} catch (Exception e){}
               try{stmt0.close();} catch (Exception e){}
               try{stmt1.close();} catch (Exception e){}
               try{stmt2.close();} catch (Exception e){}
               try{stmt3.close();} catch (Exception e){}
               
                    try{con.close();} catch (Exception e){}
           } 
        return 1;
        }
       
       public static void manual_daily(int a)
       {
           String s_gift,r_gift;
             
             Connection con=null;
             Statement stmt1=null;
             ResultSet rs=null;
        try
         {
             shared_through="DAILY";
            Class.forName("oracle.jdbc.driver.OracleDriver");
             con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","welcome");
             stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            
             if(signal1)
             {
                stmt1.executeQuery("delete recipient");
                   c.clear();
                   signal1=false;
                  
             }
                
             rs=stmt1.executeQuery("select id,name from users where id='"+a+"'"); 
            if(rs.next())
            {
                
            if(!(c.containsKey(a)))
            {
                
                            if(day==1)
                            {
                            r_gift=arr[new Random().nextInt(1)];
                            }
                            else if(day==2)
                            {
                                r_gift=arr[new Random().nextInt(2)];
                            }
                            else
                            {
                                r_gift=arr[new Random().nextInt(3)];
                            }
                           
                            System.out.println(Christmas.get_name(a)+" "+"received "+r_gift+" as daily gift");
                            Christmas.update_gift(a,"",r_gift);
                            Christmas.update_act(a);
                            
                            c.put(a,1);
            }
             else
                        {
                            System.out.println("Only One Daily Reward per Person for a Single Day");
                          
            }
            }
            else
            {
                System.out.println(a+" isn't participating in the Event");
                
            }
           
         }
        
        
         catch(SQLIntegrityConstraintViolationException e)
     {
         
             System.out.println("Primary Key Violation in Transaction");
         
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
        finally
           {
               try{rs.close();} catch (Exception e){}
               try{stmt1.close();} catch (Exception e){}
               try{con.close();} catch (Exception e){}
           }
       }
       public static void manual_gift(int a,int u_index,int b)
       {
           String s_gift,r_gift;
             
             s_gift=arr[u_index];
             Connection con=null;
             Statement stmt0,stmt1,stmt2,stmt3,stmt4;
             ResultSet rs0,rs,rs1,rs2,rs3;
             stmt0=stmt1=stmt2=stmt3=stmt4=null;rs0=rs=rs1=rs2=rs3=null;
        try
         {
             shared_through="SHARING";
             
              Class.forName("oracle.jdbc.driver.OracleDriver");
              
             con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","welcome");
             stmt0=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                
             stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             stmt2=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             stmt3=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            stmt4=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            if(signal2)
             {
                stmt0.executeQuery("delete recipient");
                   d.clear();
                   signal2=false;
             }
            
               
         label1 :
               {
                   rs0=stmt0.executeQuery("select id,name from users where id='"+a+"' ");
                   if(rs0.next())
                   {
                       ResultSet rs4=stmt0.executeQuery("select id,name from users where id='"+b+"' ");
                       if(rs4.next())
                       {
                     rs1=stmt1.executeQuery("select TREE,BULB,CANDY_STICK,BELL,STAR from GIFT where ID='"+a+"'");
                    if(rs1.next())
                    {  
                      rs3=stmt4.executeQuery("select TREE,BULB,CANDY_STICK,BELL,STAR from GIFT where ID='"+a+"'");
                    while(rs3.next())
                    {      
                    if((a!=b)&&((rs3.getInt(1)>0)||(rs3.getInt(2)>0)||(rs3.getInt(3)>0)||(rs3.getInt(4)>0)||(rs3.getInt(5)>0)))

                    { 
                      
                        
                           if(d.containsKey(a))
                                {
                                    
                                     rs2=stmt2.executeQuery("select p1,p2,p3,p4,p5 from recipient where id='"+a+"' ");

                                        if(rs2.next())
                                            {
                                                
                                                 rs=stmt3.executeQuery("select p1,p2,p3,p4,p5 from recipient where id='"+a+"' ");
                                                
                                                while(rs.next())
                                                    {

                                                        if(((b!=rs.getInt(1))&&(b!=rs.getInt(2))&&(b!=rs.getInt(3))&&(b!=rs.getInt(4))&&(b!=rs.getInt(5))))
                                                            {
                                                                if((d.get(a)<5))
                                                                {
                                                                
                                                                 Arrays.sort(rndm);
                                                                if((Arrays.binarySearch(rndm,s_gift))>=0)
                                                                {
                                                                Christmas.rndm_gift(a);
                                                                r_gift=Christmas.get_sharing_gift(a);
                                                                System.out.println(Christmas.get_name(a)+" "+"shared "+s_gift+" to "+Christmas.get_name(b));
                                                                System.out.println(Christmas.get_name(a)+" "+"received "+r_gift+" as reward");
                                                                Christmas.update_gift(a,s_gift,r_gift);
                                                                Christmas.update_gift(b,"",s_gift);
                                                                Christmas.update_act(a);
                                                                Christmas.update_recipient(a,b,d.get(a)+1);
                                                                d.put(a,d.get(a)+1);
                                                                }
                                                                else
                                                                {
                                                                    System.out.println("You Don't have that Gift to Share");
                                                                    break label1;
                                                                }
                                                            }
                                                                else
                                                                {System.out.println("Only 5 Sharings per person");
                                                                    break label1;
                                                                   }
                                                            }else
                                                            {
                                                                System.out.println("You cannot share to the Same Person");
                                                                
                                                                break label1;
                                                            }
                                                    }
                                                
                                            }
                                            else
                                            {
                                                throw new NoRecordException ("Record Not Found in Recipient");
                                             }

                                }
                                else
                                {
                                    
                                        Arrays.sort(rndm);
                                     if((Arrays.binarySearch(rndm,s_gift))>=0)
                                        {
                                           Christmas.rndm_gift(a);
                                          r_gift=Christmas.get_sharing_gift(a);
                                          System.out.println(Christmas.get_name(a)+" "+"shared "+s_gift+" to "+Christmas.get_name(b));
                                          System.out.println(Christmas.get_name(a)+" "+"received "+r_gift+" as reward");
                                          Christmas.update_gift(a,s_gift,r_gift);
                                          Christmas.update_gift(b,"",s_gift);
                                          Christmas.update_act(a);
                                          Christmas.update_recipient(a,b,1);
                                            d.put(a,1);
                                                }
                                                else
                                                   {
                                                      System.out.println("You Don't have that Gift to Share");
                                                        break label1;
                                                   }
                                }
                    }else
                    {
                        
                        System.out.println(Christmas.get_name(a)+" doesn't have any Gifts to Share");
                        break label1;
                    }
                    
                    }
                    
          
                
                
            }
              else
                {
                  System.out.println(Christmas.get_name(a)+" doesn't have any Gifts to Share");
                }
                       }
                       else
                       {
                           System.out.println("Receiver "+b+" isn't participating in the Event");
                       }
               
            }
                   else
         {
                 System.out.println("Sender "+a+" isn't participating in the Event");
          }
               }
         
            
            
            }
         
        catch(NoRecordException e)
        {
            System.out.println(e);
        }
         catch(SQLIntegrityConstraintViolationException e)
     {
         
             System.out.println("Primary Key Violation in Transaction");
         
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
        finally
           {
               try{rs0.close();} catch (Exception e){}
               try{rs.close();} catch (Exception e){}
               try{rs1.close();} catch (Exception e){}
               try{rs2.close();} catch (Exception e){}
               try{rs3.close();} catch (Exception e){}
               try{stmt0.close();} catch (Exception e){}
               try{stmt1.close();} catch (Exception e){}
               try{stmt2.close();} catch (Exception e){}
               try{stmt3.close();} catch (Exception e){}
               try{stmt4.close();} catch (Exception e){}
                    try{con.close();} catch (Exception e){}
           }
       }
       public static void manual_transaction(int a,double amt,int b)
       {
           String s_gift,r_gift;
             
             Connection con=null;
             Statement stmt0,stmt1,stmt2,stmt3;
             ResultSet rs0,rs,rs1,rs2,rs3,rs4;
             stmt0=stmt1=stmt2=stmt3=null;rs0=rs=rs1=rs2=rs3=rs4=null;
             
        try
         {
             shared_through="TRANSACTION";
             
              Class.forName("oracle.jdbc.driver.OracleDriver");
              
             con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","welcome");
            stmt0=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                
             stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             stmt2=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             stmt3=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            if(signal3)
             {
                 
                stmt0.executeQuery("delete recipient");
                stmt0.executeQuery("delete transaction");
                   e.clear();
                   signal3=false;
             }
            
                label2 :
                {
                     rs0=stmt0.executeQuery("select id,name from users where id='"+a+"' ");
                   if(rs0.next())
                   {
                   
                        rs3=stmt0.executeQuery("select id,name from users where id='"+b+"' ");
                   if(rs3.next())
                   {
             rs1=stmt1.executeQuery("select acc_bal from users where id='"+a+"' ");
            while(rs1.next())
            {
            if((a!=b)&&((rs1.getInt(1)>amt)))
               
            {
                
             
            if(e.containsKey(a))
            {  
                 rs2=stmt2.executeQuery("select p1,p2,p3,p4,p5 from recipient where id='"+a+"' ");
                
                if(rs2.next())
                {
                        
                        
                     rs=stmt3.executeQuery("select p1,p2,p3,p4,p5 from recipient where id='"+a+"' ");
                    while(rs.next())
                    {
                        
                        if(((b!=rs.getInt(1))&&(b!=rs.getInt(2))&&(b!=rs.getInt(3))))
                        {
                            if(e.get(a)<3)
                            {
                            r_gift=Christmas.get_transaction_gift(a,amt);
                            System.out.println(Christmas.get_name(a)+" "+"sent ₹"+amt+" to "+Christmas.get_name(b));
                            System.out.println(Christmas.get_name(a)+" "+"received "+r_gift+" as return gift");
                            Christmas.update_gift(a,"",r_gift);
                            Christmas.update_transaction(a,amt,b);
                            Christmas.update_act(a);
                            Christmas.update_recipient(a,b,e.get(a)+1);
                            e.put(a,e.get(a)+1);
                        }
                        else
                        {
                            System.out.println("Only 3 Transactions per Person");
                            break label2;
                        }
                        }
                        else
                        {
                            System.out.println("You cannot Transact to the Same Person");
                           
                            break label2;
                        }
                    }
                }
                else
                {
                   throw new NoRecordException ("Record Not Found in Recipient()Transaction");
                }
                
            }
            else
            {
                            r_gift=Christmas.get_transaction_gift(a,amt);
                            System.out.println(Christmas.get_name(a)+" "+"sent ₹"+amt+" to "+Christmas.get_name(b));
                            System.out.println(Christmas.get_name(a)+" "+"received "+r_gift+" as return gift");
                            Christmas.update_gift(a,"",r_gift);
                            Christmas.update_transaction(a,amt,b);
                            Christmas.update_act(a);
                            Christmas.update_recipient(a,b,1);
                            e.put(a,1);
                            break label2;
            }
         }
            else
            {
                System.out.println("Not Enough Balance in your Account");
                break label2;
            }
            }
                   }
                   else
                   {
                       System.out.println("Receiver "+b+" isn't participating in the Event");
                   }
                }
                   else
                   {
                       System.out.println("Sender "+a+" isn't participating in the Event");
                   }
                }
            
            
         }
        catch(NoRecordException e)
        {
            System.out.println(e);
        }
         catch(SQLIntegrityConstraintViolationException e)
     {
         
             System.out.println("Primary Key Violation in Transaction");
         
     }
        
     catch(Exception e)
     {
         System.out.println(e);
     }
        finally
           {
               try{rs0.close();} catch (Exception e){}
               try{rs.close();} catch (Exception e){}
               try{rs1.close();} catch (Exception e){}
               try{rs2.close();} catch (Exception e){}
               try{rs3.close();} catch (Exception e){}
               try{rs4.close();} catch (Exception e){}
               try{stmt0.close();} catch (Exception e){}
               try{stmt1.close();} catch (Exception e){}
               try{stmt2.close();} catch (Exception e){}
               try{stmt3.close();} catch (Exception e){}
               
                    try{con.close();} catch (Exception e){}
           } 
       }
       public static void auto()
       {
           boolean done=true;
           day=1;
           String choice="";
           control="auto";
           Random r=new Random();
           int a,b;
           Scanner sc=new Scanner(System.in);
           while(done)
           {
               System.out.println("***************************");
               System.out.println("-----Its Day "+day+"-----");
               System.out.println("1.Daily Reward");
               System.out.println("2.Share Gifts");
               System.out.println("3.Do Transaction");
               if(day<=6)
               {
                   System.out.println("4.Increment Day");
               }
               if(day==7&&win==1)
               {
                   System.out.println("W.Display Winners");
               }
               System.out.println("B.Back to Main Menu");
               System.out.println("***************************");
               System.out.println("Select Your Choice");
               choice=sc.next();
               switch(choice)
               {
                   case "1":Christmas.auto_daily();
                            break;
                   case "2":x1=1;
                                for(int i1=1;i1<=25;i1++)
                                {
                                    a=min+(r.nextInt(max));
                                     b=min+(r.nextInt(max));
                                    if((Christmas.auto_gifts(a,b))==0)
                                    {
                                        i1=i1-1;
                                    }
                                        }
                             break;
                   case "3":x2=1;double amt,min_amt=50.00;
                                    for(int i2=1;i2<=25;i2++)
                                    {
                                         a=min+(r.nextInt(max));
                                         b=min+(r.nextInt(max));
                                        amt=min_amt+Math.round(300*(r.nextDouble()));
                                        if((Christmas.auto_transaction(a,amt,b))==0)
                                        {
                                            i2=i2-1;
                                        }
                                    }
                        
                             break;
                   case "4":day++;
                            break;
                   case "W":Christmas.display_winners();
                            break;
                   case "w":Christmas.display_winners();
                            break;
                   case "B":done=false;
                             return;
                   case "b":done=false;
                             return;
                   default:System.out.println("Didn't get your Choice,Try Again");
               }
           }
       }
       public static void manual()
       {
           boolean done=true;
           day=1;
           control="manual";
           signal1=signal2=signal3=true;
           int id1,id2,id3,u_index;
           double u_amt;
           
           Scanner sc=new Scanner(System.in);
           String name,ph_no,email_id,loc,choice="";
           while(done)
           {
               System.out.println("***************************");
               System.out.println("-----Its Day "+day+"-----");
               System.out.println("1.Claim Daily Reward");
               System.out.println("2.Share Gifts");
               System.out.println("3.Perform Transactions");
               if(day<=6)
               {
                   System.out.println("4.Increment Day");
               }
               System.out.println("5.Add a Participant");
               System.out.println("6.Delete a Participant");
               System.out.println("B.Back to Main Menu");
               System.out.println("***************************");
               System.out.println("Select Your Choice");
               choice=sc.next();
               switch(choice)
               {
                   case "1":System.out.println("Enter ID of Daily Reward to be claimed");
                            id1=sc.nextInt();
                            Christmas.manual_daily(id1);
                            break;
                   case "2":signal3=true;
                            System.out.println("Enter the Sender's and Receiver's ID");
                            id1=sc.nextInt();
                            id2=sc.nextInt();
                            if(id1==id2)
                            {
                                System.out.println("Self Share is not Allowed");
                            break;}
                            
                            System.out.println("Enter the index of a gift shown below to Share:");
                            Christmas.rndm_gift(id1);
                            u_index=sc.nextInt();
                            Christmas.manual_gift(id1,u_index,id2);
                            break;
                   case "3":signal2=true;
                            System.out.println("Enter the Sender's and Receiver's ID");
                            id1=sc.nextInt();
                            id2=sc.nextInt();
                            if(id1==id2)
                            {
                                System.out.println("Self Transaction is not Allowed");
                            break;}
                            
                            System.out.println("Enter the Amount to be Transacted");
                            u_amt=sc.nextDouble();
                            Christmas.manual_transaction(id1,u_amt,id2);
                            break;
                   
                   case "4":if(day==7)
                            {
                                System.out.println("Final Day Reached");
                                break;
                            }
                                   day++;
                   
                            signal1=signal2=signal3=true;
                            break;
                   case "5": 
                             System.out.println("Enter Name");
                             name=sc.next();
                             System.out.println("Enter Phone Number");
                             ph_no=sc.next();
                             System.out.println("Enter Email_id");
                             email_id=sc.next();
                             System.out.println("Enter Location");
                             loc=sc.next();
                             System.out.println("Enter Bank Balance");
                             u_amt=sc.nextDouble();
                             System.out.println("ID of new User is "+Christmas.add_user(name,ph_no,email_id,loc,u_amt));
                             break;
                   case "6":
                            System.out.println("Enter the ID of User to be Remove");
                            id3=sc.nextInt();
                            Christmas.delete_user(id3);
                            break;
                   case "B":done=false;
                             return;
                   case "b":done=false;
                             return;
                   default:System.out.println("Didn't get your Choice,Try Again");          
               }
           }
       }
       public static void main(String [] args)
       {
           boolean done=true;
           System.out.println("Welcome to Christmas Event");
           Scanner sc=new Scanner(System.in);
           String choice="";
            while(done)
           {
              System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
              System.out.println("1.Auto");
              System.out.println("2.Manual");
              System.out.println("E.Exit");
              System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
              System.out.println("Select a Mode You want to work on");
              choice=sc.next();
              switch(choice)
              {
                  case "1":Christmas.auto();
                           break;
                  case "2":Christmas.manual();
                           break;
                  case "E":done=false;
                           break;
                  case "e":done=false;
                           break;
                  default:System.out.println("Oops!!Your Choice isn't in the Menu");
              }
           }
       }
}
