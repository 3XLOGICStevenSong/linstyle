package com.djb.highway.framework.dao;

import java.util.List;
import java.util.Map;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;

public interface BaseDAO<E> {
    public int insert() throws KeyExistException;

    public int insert(Object parameter) throws KeyExistException;

    public int insert(String statement) throws KeyExistException;

    public int insert(String statement, Object parameter)
            throws KeyExistException;

    public int insertBatch(List<?> list) throws KeyExistException;

    public int insertBatch(String statement, List<?> list)
            throws KeyExistException;

    public int delete() throws DataNotFoundException;

    public int delete(Object parameter) throws DataNotFoundException;

    public int delete(String statement) throws DataNotFoundException;

    public int delete(String statement, Object parameter)
            throws DataNotFoundException;

    public int deleteBatch(List<?> list) throws DataNotFoundException;

    public int deleteBatch(String statement, List<?> list)
            throws DataNotFoundException;

    public int update() throws DataNotFoundException;

    public int update(Object parameter) throws DataNotFoundException;

    public int update(String statement) throws DataNotFoundException;

    public int update(String statement, Object parameter)
            throws DataNotFoundException;

    public int updateBatch(List<?> list) throws DataNotFoundException;

    public int updateBatch(String statement, List<?> list)
            throws DataNotFoundException;

    public E getObject();

    public E getObject(Object parameter);

    public E getObject(String statement);

    public E getObject(String statement, Object parameter);

    public <T> T getOtherObject(Class<T> clazz);

    public <T> T getOtherObject(Object parameter, Class<T> clazz);

    public <T> T getOtherObject(String statement, Class<T> clazz);

    public <T> T getOtherObject(String statement, Object parameter, Class<T> clazz);

    public List<E> findList();

    public List<E> findList(Object parameter);

    public List<E> findList(String statement);

    public List<E> findList(String statement, Object parameter);

    public <T> List<T> findOtherList(Class<T> clazz);

    public <T> List<T> findOtherList(Object parameter, Class<T> clazz);

    public <T> List<T> findOtherList(String statement, Class<T> clazz);

    public <T> List<T> findOtherList(String statement, Object parameter,
            Class<T> clazz);

    public List<E> findListByCondition(Object parameter);
    
    public <T> List<T> findOtherListByCondition(Object parameter, Class<T> clazz);

    public PageModel findPagedList(PageModel parameter);

    public PageModel findPagedList(String statement, PageModel parameter);

    public Map<?, E> selectMap(String mapKey);

    public Map<?, E> selectMap(Object parameter, String mapKey);

    public Map<?, E> selectMap(String statement, String mapKey);

    public Map<?, E> selectMap(String statement, Object parameter, String mapKey);

}
