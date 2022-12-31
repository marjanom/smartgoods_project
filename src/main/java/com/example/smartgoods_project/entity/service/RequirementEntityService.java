package com.example.smartgoods_project.entity.service;

import com.example.smartgoods_project.entity.models.Requirement;
import com.example.smartgoods_project.entity.repository.RequirementRepository;
import com.example.smartgoods_project.entity.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RequirementEntityService implements RequirementRepository {

    RequirementRepository requirementRepository;


    @Override
    public List<Requirement> findAllByUserId(Long userId) {
        return requirementRepository.findAllByUserId(userId);
    }

    @Override
    public Requirement findByUserId(String userId) {
        return null;
    }

    @Override
    public <S extends Requirement> S save(S entity) {
        return requirementRepository.save(entity);
    }




    @Override
    public List<Requirement> findAll() {
        return null;
    }

    @Override
    public List<Requirement> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Requirement> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Requirement> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        requirementRepository.deleteById(aLong);
    }

    @Override
    public void delete(Requirement entity) {

    }


    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Requirement> entities) {

    }

    @Override
    public void deleteAll() {

    }



    @Override
    public <S extends Requirement> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Requirement> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return requirementRepository.existsById(aLong);
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Requirement> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Requirement> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Requirement> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Requirement getOne(Long aLong) {
        return null;
    }

    @Override
    public Requirement getById(Long aLong) {
        return null;
    }

    @Override
    public Requirement getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Requirement> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Requirement> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Requirement> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Requirement> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Requirement> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Requirement> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Requirement, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }


}
