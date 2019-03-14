package com.djb.ylt.framework.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.djb.ylt.common.util.Assert;
import com.djb.ylt.framework.entity.BaseEntity;
import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;

public abstract class BaseDAOImpl<E> extends SqlSessionDaoSupport implements BaseDAO<E> {

    public static final String COUNT_POST = "_count";
    public static final String MAPPER_INSERT = "insert";
    public static final String MAPPER_INSERTBATCH = "insertBatch";
    public static final String MAPPER_DELETE = "delete";
    public static final String MAPPER_DELETEBATCH = "deleteBatch";
    public static final String MAPPER_UPDATE = "update";
    public static final String MAPPER_UPDATEBATCH = "updateBatch";
    public static final String MAPPER_GETOBJECT = "getObject";
    public static final String MAPPER_FINDLIST = "findList";
    public static final String MAPPER_FINDPAGEDLIST = "findPagedList";
    public static final String FINDLISTBYCONDITION = "findListByCondition";
    public static final String MAPPER_SELECTMAP = "selectMap";
    private Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public BaseDAOImpl() {
        Type type = getClass().getGenericSuperclass();
        Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
        this.entityClass = (Class<E>) trueType;
    }

    @Override
    public int delete() throws DataNotFoundException {
        return delete(generateStatement(MAPPER_DELETE));
    }

    @Override
    public int delete(Object parameter) throws DataNotFoundException {
        return delete(generateStatement(MAPPER_DELETE), parameter);
    }

    @Override
    public int delete(String statement) throws DataNotFoundException {
        return delete(statement, null);
    }

    @Override
    public int delete(String statement, Object parameter) throws DataNotFoundException {
        Assert.notEmpty(statement, "Property statement is required");
        return getSqlSession().delete(statement, parameter);
    }

    @Override
    public int deleteBatch(List<?> list) throws DataNotFoundException {
        return deleteBatch(generateStatement(MAPPER_DELETEBATCH), list);
    }

    @Override
    public int deleteBatch(String statement, List<?> list) throws DataNotFoundException {
        Assert.notEmpty(statement, "Property statement is required");
        if (list == null || list.isEmpty()) {
            return 0;
        }
        SqlSession sqlSession = getSqlSession();
        for (int i = 0; i < list.size(); i++) {
            sqlSession.delete(statement, list.get(i));
        }
        return list.size();
    }

    @Override
    public List<E> findList() {
        return findList(MAPPER_FINDLIST);
    }

    @Override
    public List<E> findList(Object parameter) {
        return findList(MAPPER_FINDLIST, parameter);
    }

    @Override
    public List<E> findListByCondition(Object parameter) {
        return findList(FINDLISTBYCONDITION, parameter);
    }

    @Override
    public <T> List<T> findOtherListByCondition(Object parameter, Class<T> clazz) {
        return (List<T>) findOtherList(FINDLISTBYCONDITION, parameter, clazz);
    }

    @Override
    public List<E> findList(String statement) {
        return findList(statement, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findList(String statement, Object parameter) {
        Assert.notEmpty(generateStatement(statement), "Property statement is required");
        return (List<E>) getSqlSession().selectList(generateStatement(statement), parameter);
    }

    @Override
    public <T> List<T> findOtherList(Class<T> clazz) {
        return (List<T>) findOtherList(MAPPER_FINDLIST, clazz);
    }

    @Override
    public <T> List<T> findOtherList(Object parameter, Class<T> clazz) {
        return (List<T>) findOtherList(MAPPER_FINDLIST, parameter, clazz);
    }

    @Override
    public <T> List<T> findOtherList(String statement, Class<T> clazz) {
        return (List<T>) findOtherList(statement, null, clazz);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> findOtherList(String statement, Object parameter, Class<T> clazz) {
        Assert.notEmpty(generateStatement(parameter, statement), "Property statement is required");
        return (List<T>) getSqlSession().selectList(generateStatement(parameter, statement), parameter);
    }

    @Override
    public E getObject() {
        return getObject(MAPPER_GETOBJECT);
    }

    @Override
    public E getObject(Object parameter) {
        return getObject(MAPPER_GETOBJECT, parameter);
    }

    @Override
    public E getObject(String statement) {
        return getObject(statement, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E getObject(String statement, Object parameter) {
        Assert.notEmpty(generateStatement(statement), "Property statement is required");
        return (E) getSqlSession().selectOne(generateStatement(statement), parameter);
    }

    @Override
    public <T> T getOtherObject(Class<T> clazz) {
        return (T) getOtherObject(MAPPER_GETOBJECT, clazz);
    }

    @Override
    public <T> T getOtherObject(Object parameter, Class<T> clazz) {
        return (T) getOtherObject(MAPPER_GETOBJECT, parameter, clazz);
    }

    @Override
    public <T> T getOtherObject(String statement, Class<T> clazz) {
        return (T) getOtherObject(statement, null, clazz);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getOtherObject(String statement, Object parameter, Class<T> clazz) {
        Assert.notEmpty(generateStatement(parameter, statement), "Property statement is required");
        return (T) getSqlSession().selectOne(generateStatement(parameter, statement), parameter);
    }

    @Override
    public int insert() throws KeyExistException {
        return insert(generateStatement(MAPPER_INSERT));
    }

    @Override
    public int insert(Object parameter) throws KeyExistException {
        return insert(generateStatement(MAPPER_INSERT), parameter);
    }

    @Override
    public int insert(String statement) throws KeyExistException {
        return insert(statement, null);
    }

    @Override
    public int insert(String statement, Object parameter) throws KeyExistException {
        Assert.notEmpty(statement, "Property statement is required");
        return getSqlSession().insert(statement, parameter);
    }

    @Override
    public int insertBatch(List<?> list) throws KeyExistException {
        return insertBatch(generateStatement(MAPPER_INSERTBATCH), list);
    }

    @Override
    public int insertBatch(String statement, List<?> list) throws KeyExistException {
        Assert.notEmpty(statement, "Property statement is required");
        if (list == null || list.isEmpty()) {
            return 0;
        }
        SqlSession sqlSession = getSqlSession();
        for (int i = 0; i < list.size(); i++) {
            sqlSession.insert(statement, list.get(i));
        }
        return list.size();
    }

    @Override
    public Map<?, E> selectMap(String mapKey) {
        return selectMap(generateStatement(MAPPER_SELECTMAP), mapKey);
    }

    @Override
    public Map<?, E> selectMap(Object parameter, String mapKey) {
        return selectMap(generateStatement(MAPPER_SELECTMAP), parameter, mapKey);
    }

    @Override
    public Map<?, E> selectMap(String statement, String mapKey) {
        return selectMap(statement, null, mapKey);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<?, E> selectMap(String statement, Object parameter, String mapKey) {
        Assert.notEmpty(statement, "Property statement is required");
        Assert.notEmpty(statement, "Property mapKey is required");
        return getSqlSession().selectMap(statement, parameter, mapKey);
    }

    @Override
    public int update() throws DataNotFoundException {
        return update(generateStatement(MAPPER_UPDATE));
    }

    @Override
    public int update(Object parameter) throws DataNotFoundException {
        return update(generateStatement(MAPPER_UPDATE), parameter);
    }

    @Override
    public int update(String statement) throws DataNotFoundException {
        return update(statement, null);
    }

    @Override
    public int update(String statement, Object parameter) throws DataNotFoundException {
        Assert.notEmpty(statement, "Property statement is required");
        return getSqlSession().update(statement, parameter);
    }

    @Override
    public int updateBatch(List<?> list) throws DataNotFoundException {
        return updateBatch(generateStatement(MAPPER_UPDATEBATCH), list);
    }

    @Override
    public int updateBatch(String statement, List<?> list) throws DataNotFoundException {
        Assert.notEmpty(statement, "Property statement is required");
        if (list == null || list.isEmpty()) {
            return 0;
        }
        SqlSession sqlSession = getSqlSession();
        for (int i = 0; i < list.size(); i++) {
            sqlSession.update(statement, list.get(i));
        }
        return list.size();
    }

    @Override
    public PageModel findPagedList(PageModel parameter) {
        return findPagedList(MAPPER_FINDPAGEDLIST, parameter);
    }

    @SuppressWarnings("unchecked")
    @Override
    public PageModel findPagedList(String statement, PageModel parameter) {

        statement = generateStatement(statement);
        String countStatement = statement + COUNT_POST;
        Assert.notEmpty(statement, "Property statement is required");
        Assert.notEmpty(countStatement, "Property countStatement is required");

        List<E> list = new ArrayList<E>();
        if (parameter == null) {
            return new PageModel();
        }
        if (parameter.getTotalItem() <= 0) {
            Integer totalItem = (Integer) getSqlSession().selectOne(countStatement, parameter);
            if (totalItem != null) {
                parameter.setTotalItem(totalItem.intValue());
            } else if (totalItem == null || totalItem == 0) {
                return new PageModel();
            }
        }
        list = (List<E>) getSqlSession().selectList(statement, parameter);
        parameter.setList((List<? extends BaseEntity>) list);
        return parameter;
    }

    public String generateStatement(String mapperId) {
        return entityClass.getName() + "." + mapperId;
    }

    public String generateStatement(Object obj, String mapperId) {
        return obj == null ? (mapperId) : obj.getClass().getName() + "." + mapperId;
    }

}
