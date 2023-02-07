package com.ssafy.db.repository;

import com.ssafy.api.request.TodoRequest;
import com.ssafy.db.entity.Todo.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository @Primary
@RequiredArgsConstructor
public class TodoRepositorySupport implements TodoRepository {

    private final EntityManager em;

    @Override
    public void saveTodo(Todo todo){
        em.persist(todo);       // persist : 데이터 삽입
    }

    @Override
    public void removeTodo(Long todo_id){
        em.remove(findById(todo_id));
    }
    
    // Read
    @Override
    public Todo findById(Long todo_id) {
        return em.find(Todo.class, todo_id);
    }

    @Override
    public Todo findByNickname(String nickname) {
        try {
            return em.createQuery("select t from Todo t where t.nickname=:nickname", Todo.class)
                    .setParameter("nickname", nickname)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Todo> todoList(String nickname, String datetime) {
        try {
            return em.createQuery("select t from Todo t Where t.nickname=:nickname and t.datetime=:datetime", Todo.class)
                    .setParameter("nickname", nickname)
                    .setParameter("datetime", datetime)
                    .getResultList();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Todo> searchTodo(String nickname, String content) {
        try {
            return em.createQuery("select t from Todo t Where t.nickname=:nickname and t.content like :content", Todo.class)
                    .setParameter("nickname", nickname)
                    .setParameter("content", "%"+content+"%")
                    .getResultList();
        } catch (NoResultException e){
            return null;
        }
    }

}
