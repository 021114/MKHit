import com.mk.po.Student;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Test {

    @org.junit.Test
    public void test() {
        //1.获取SessionFactory  会话工厂
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //2.生产会话  通过openSession  获取session对象
        Session session = sessionFactory.openSession();
        //3.开启事务
        Transaction transaction = session.beginTransaction();

        //4.1执行查询（查询单条数据）  get(要查询的实体类型,参数)
        Student student = session.get(Student.class, 1);
        System.out.println("student="+student);
        //4.2执行查询（查询单条数据）
        Student student1 = session.get(Student.class, 1);
        System.out.println("student1="+student1);
        //4.3执行查询（查询单条数据）  hql 语句
        String hql1="from Student where id=?";
        String hql2="from Student where id=:id";
        Query query = session.createQuery(hql2);
        //query.setParameter(0,1);
        query.setParameter("id",3);
        Object o = query.uniqueResult();//只针对于 已经知道只有一条语句时才能使用
        System.out.println("o="+o);

        //5.查询全部
        Query query1 = session.createQuery("from Student");
        List<Student> list=query1.list();
        for (Student stu : list) {
            System.out.println("stu="+stu);
        }
        SQLQuery sqlQuery = session.createSQLQuery("select * from student");
        sqlQuery.addEntity(Student.class);
        List<Student> list1=sqlQuery.list();
        for (Student stu : list1) {
            System.out.println("stu="+stu);
        }

        //增加操作
        Student stu=new Student();
        stu.setName("赵六");
        session.save(stu);


        //删除操作  现根据id查询是否存在对象(entity) 再去删除
        Student stu1=session.get(Student.class,2);
        if(stu1!=null){
            session.delete(stu1);
        }

        //修改操作
        Student student2=new Student();
        student2.setId(3);
        student2.setName("哈哈");
        session.update(student2);


        //新增或修改  根据实体类中的 主键 进行查询
        Student student3=new Student();
        student3.setName("哈哈123");
        session.saveOrUpdate(student3);


        /*transaction.commit();//提交事务
        session.close();//关闭session
        session=sessionFactory.openSession();
        transaction=session.beginTransaction();*/
    }
}