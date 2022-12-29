package com.example.smartgoods_project.entity.service;

import com.example.smartgoods_project.entity.models.Requirment;
import com.example.smartgoods_project.entity.repository.RequirmentRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class RequirmentEntityService implements RequirmentRepository {


    @Override
    public List<Requirment> findAll() {
        return null;
    }

    @Override
    public List<Requirment> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Requirment> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Requirment> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Requirment entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Requirment> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Requirment> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Requirment> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Requirment> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Requirment> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Requirment> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Requirment> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Requirment getOne(Long aLong) {
        return null;
    }

    @Override
    public Requirment getById(Long aLong) {
        return null;
    }

    @Override
    public Requirment getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Requirment> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Requirment> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Requirment> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Requirment> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Requirment> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Requirment> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Requirment, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
