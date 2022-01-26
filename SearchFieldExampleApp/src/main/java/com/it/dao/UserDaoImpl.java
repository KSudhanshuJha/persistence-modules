package com.it.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.it.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	public int saveUser(User user) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			int userId = (Integer) session.save(user);
			return userId;
		} catch (Exception exception) {
			return 0;
		} finally {
			session.flush();
		}

	}

	public List<User> getFilteredData(User user) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			ArrayList<Object> listField = new ArrayList<>();
			ArrayList<Object> listValue = new ArrayList<>();
			if (user.getName() == null || user.getName() == "") {
			} else {
				listField.add("name");
				listValue.add(user.getName());
			}
			if (user.getEmailId() == null || user.getEmailId() == "") {
			} else {
				listField.add("emailId");
				listValue.add(user.getEmailId());
			}
			switch (listField.size()) {
			case 0:
				Query<User> query0 = session.createQuery("from User");
				return query0.list();
			case 1:
				Query query1 = session.createQuery("from User where " + listField.get(0) + " = :value0");
				query1.setParameter("value0", listValue.get(0));
				return query1.list();
			case 2:
				Query query2 = session.createQuery(
						"from User where " + listField.get(0) + " =:value0 and " + listField.get(1) + " =:value1");
				query2.setParameter("value0", listValue.get(0));
				query2.setParameter("value1", listValue.get(1));
				return query2.list();
			}
			return null;
		}
		catch (Exception exception) {
			throw new RuntimeException("Filtering Data Exception");
		} finally {
			session.flush();
		}

	}

}
