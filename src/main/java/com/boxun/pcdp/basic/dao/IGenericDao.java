package com.boxun.pcdp.basic.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;

public interface IGenericDao <E extends Serializable, PK extends Serializable> {

	/*
	T load(PK id);
	
	T get(PK id);
	
	List<T> findAll();
	
	void persist(T entity);
	
	PK save(T entity);
	
	void saveOrUpdate(T entity);
	
	void delete(PK id);
	
	void flush();
	
	*/
	enum Stack {  
        MAX, MIN, AVG, SUM;  
    }  
  
    /** 
     *  
     * @param id 
     *            ��������ѯһ��ʵ�� 
     * @return һ��ʵ����� 
     */  
    E get(PK id);  
  
    /** 
     *  
     * @param id 
     *            ��������ѯһ��ʵ�� 
     * @param lock 
     *            ����ʵ�� 
     * @return һ��ʵ����� 
     */  
    E get(PK id, LockMode lock);  
  
    /** 
     * ʹ����ݿ⺯�� 
     *  
     * @param criteria 
     *            һ��DetacherCriteria���� 
     * @param propertyName 
     *            ʵ���������� 
     * @param stackName 
     *            Stackö�������е�����һ�� 
     * @return һ��һ����ݿ� 
     */  
    Object getStackValue(DetachedCriteria criteria, String propertyName,  
            String stackName);  
  
    /** 
     * ��ѯ��ݿ��Ӧ�ļ�¼�� 
     *  
     * @param criteria 
     *            һ��DetachedCriteria���� 
     * @return ��¼�� 
     */  
    Long getRowCount(DetachedCriteria criteria);  
  
    /** 
     *  
     * @param id 
     *            �������Ӳ�һ��ʵ����� 
     * @return һ��ʵ����� 
     */  
    E load(PK id);  
  
    /** 
     *  
     * @param id 
     *            �������Ӳ�ʵ�� 
     * @param lock 
     *            ����ʵ�� 
     * @return һ��ʵ����� 
     */  
    E load(PK id, LockMode lock);  
  
    /** 
     *  
     * @return �Ӳ����ж��� 
     */  
    List<E> loadAll();  
  
    /** 
     *  
     * @param entity 
     *            ����һ��ʵ�� 
     * @throws HibernateException 
     *             �׳�Exception�쳣 
     */  
    void save(E entity) throws HibernateException;  
  
    /** 
     *  
     * @param entity 
     *            ɾ��һ��ʵ�� 
     * @throws HibernateException 
     *             �׳��쳣 
     */  
    void delete(E entity) throws HibernateException;  
  
    /** 
     *  
     * @param entity 
     *            ɾ��һ��ʵ�� 
     * @param lock 
     *            ����ʵ�� 
     * @throws HibernateException 
     *             �׳��쳣 
     */  
    void delete(E entity, LockMode lock) throws HibernateException;  
  
    /** 
     *  
     * @param entitys 
     *            ɾ����ʵ�� 
     * @throws HibernateException 
     *             �׳��쳣 
     */  
    void delete(Collection<E> entitys) throws HibernateException;  
  
    /** 
     *  
     * @param entity 
     *            �޸�һ��ʵ�� 
     * @throws HibernateException 
     *             �׳��쳣 
     */  
    void update(E entity) throws HibernateException;  
  
    /** 
     *  
     * @param entity 
     *            �޸�һ��ʵ�� 
     * @param lock 
     *            ����ʵ�� 
     * @throws HibernateException 
     *             �׳��쳣 
     */  
    void update(E entity, LockMode lock) throws HibernateException;  
  
    /** 
     *  
     * @param entity 
     *            ��ʵ������ݿⲻ������֮��Ӧ��¼ʱ,�򱣴�ʵ��,����ʵ��,�����ʵ�� 
     * @throws HibernateException 
     *             �׳��쳣 
     */  
    void saveOrUpdate(E entity) throws HibernateException;  
  
    /** 
     *  
     * @param entitys 
     *            ������ʵ�� 
     * @throws HibernateException 
     *             �׳��쳣 
     */  
    void saveOrUpdate(Collection<E> entitys) throws HibernateException;  
  
    /*---------------------------����hql,sql����ݿ���в���--------------------------------*/  
  
    /** 
     *  
     * @param hql 
     *            ʹ��hql��������ݿ���ɾ�Ĳ��� 
     * @return ��Ӱ���еļ�¼�� 
     */  
    Integer bulkUpdate(String hql);  
  
    /** 
     *  
     * @param hql 
     *            ʹ��hql��������ݿ���ɾ�Ĳ��� 
     * @param params 
     *            hql������ 
     * @return ��Ӱ���еļ�¼�� 
     */  
    Integer bulkUpdate(String hql, Object... values);  
  
    /** 
     *  
     * @param hql 
     *            ʹ��hql���,������� 
     * @return һ��list���� 
     */  
    List<E> find(String hql);  
  
    /** 
     *  
     * @param hql 
     *            ʹ��hql���,������� 
     * @param params 
     *            hql������ 
     * @return һ��list���� 
     */  
    List<E> find(String hql, Object... values);  
  
    /** 
     *  
     * @param queryName 
     *            ʹ�������hql�����в�ѯ 
     * @return һ��list���� 
     */  
    List<E> findByNamedQuery(String queryName);  
  
    /** 
     *  
     * @param queryName 
     *            ʹ�ô���������hql�����в�ѯ 
     * @param values 
     *            ����� 
     * @return һ��list���� 
     */  
    List<E> findByNamedQuery(String queryName, Object... values);  
  
    /** 
     *  
     * @param queryName 
     *            ʹ�ô���������hql�����в�ѯ 
     * @param params 
     *            �����<br> 
     *            Map�ļ�Ϊ������Ƽ�paramName<br> 
     *            Map��ֵ��Ϊvalues 
     * @return һ��list���� 
     */  
    List<E> findByNamedParam(String queryName, Map<String, Object> params);  
  
    /** 
     *  
     * @param queryName 
     *            ʹ�ô���������hql�����в�ѯ 
     * @param params 
     *            �����<br> 
     *            Map�ļ�Ϊ������Ƽ�paramName<br> 
     *            Map��ֵ��Ϊvalues 
     * @return һ��list���� 
     */  
    List<E> findByNamedQueryAndNamedParam(String queryName,  
            Map<String, Object> params);  
  
    /** 
     *  
     * @param criteria 
     *            ʹ��ָ���ļ�����׼�������� 
     * @return һ��list���� 
     */  
    List<E> findByCriteria(DetachedCriteria criteria);  
  
    /** 
     *  
     * @param criteria 
     *            ʹ��ָ���ļ�����׼����ҳ������� 
     * @param firstResult 
     *            ��ʼ���� 
     * @param maxResults 
     *            ���ؼ�¼�� 
     * @return һ��list���� 
     */  
    List<E> findByCriteria(DetachedCriteria criteria, Integer firstResult,  
            Integer maxResults);  
  
    /** 
     * ����ָ����ʵ�� 
     *  
     * @param entity 
     *            ʵ����� 
     *  
     * @param lock 
     *            ���� 
     */  
    void lock(E entity, LockMode lock) throws HibernateException;  
  
    /** 
     * ǿ���������µ���ݿ�,������Ҫ�����ύ��,�Ż��ύ����ݿ� 
     */  
    void flush() throws HibernateException;  
  
    /** 
     *  
     * @return ���SimpleDao��������,����һ����Ự�޹صļ������� 
     */  
    DetachedCriteria createDetachedCriteria();  
  
    /** 
     *  
     * @param c 
     *            Ϊһ��ʵ������ 
     * @return ���ָ�������ʹ���һ����Ự�޹صļ������� 
     */  
    DetachedCriteria createDetachedCriteria(Class<? extends Serializable> c);  
  
    /** 
     *  
     * @return ������Ự�󶨵ļ�����׼���� 
     */  
    Criteria createCriteria();  
}
