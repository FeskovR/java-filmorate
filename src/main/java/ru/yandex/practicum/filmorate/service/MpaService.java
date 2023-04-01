package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.mpa.MpaDao;

import java.util.List;

@Service
public class MpaService {
    @Autowired
    MpaDao mpaImpl;

    public Mpa getMpaById(int id) {
        Mpa mpa = mpaImpl.getMpaById(id);
        if (mpa == null) {
            throw new RuntimeException("Mpa не найдено");
        }
        return mpa;
    }

    public List<Mpa> getAllMpa() {
        return mpaImpl.getAllMpa();
    }
}
