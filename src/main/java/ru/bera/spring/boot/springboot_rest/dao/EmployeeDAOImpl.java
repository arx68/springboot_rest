package ru.bera.spring.boot.springboot_rest.dao;


import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bera.spring.boot.springboot_rest.entity.Employee;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<Employee> getAllEmployees() {
        Session session = entityManager.unwrap(Session.class);
//        List<Employee> allEmployees = session.createQuery
//                ("from Employee", Employee.class)
//                .getResultList();
        Query<Employee> query = session.createQuery
                ("from Employee", Employee.class);
        List<Employee> allEmployees = query.getResultList();

        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);
        Employee employee = session.get(Employee.class, id);

        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);
//        Employee employee = session.get(Employee.class, id);
        Query<Employee> query = session.createQuery("delete " +
                "from Employee where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }

}
