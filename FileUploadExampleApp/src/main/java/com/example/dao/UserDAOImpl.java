package com.example.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.UserDetail;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	public int saveUser(UserDetail userDetail) {

		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			int status = (Integer) session.save(userDetail);
			return status;
		} catch (Exception exception) {
			return 0;
		} finally {
			session.flush();
		}
	}

	public UserDetail getUserDetail(int userId) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			UserDetail userDetail = session.get(UserDetail.class, userId);
			return userDetail;
		} catch (Exception exception) {
			return null;
		} finally {
			session.flush();
		}
	}

	public int updateProfileImage(String profileImage, int userID) {
		Session session = sessionFactory.getCurrentSession();
		int result;
		try {
			Query<UserDetail> query = session
					.createQuery("update UserDetail set profileImage = :profileImage where userId=:userID ");
			query.setParameter("profileImage", profileImage);
			query.setParameter("userID", userID);
			result = query.executeUpdate();
			if (result > 0) {
				return result;
			} else
				return -5;
		} catch (Exception exception) {
			return -5;
		} finally {
			session.flush();
		}
	}
}
