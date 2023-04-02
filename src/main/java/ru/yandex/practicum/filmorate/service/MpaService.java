package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.interfaces.MpaStorage;

import java.util.List;

@Service
public class MpaService {
    private MpaStorage mpaStorage;

    @Autowired
    public MpaService(MpaStorage mpaStorage) {
        this.mpaStorage = mpaStorage;
    }

    public Mpa getMpaById(int id) {
        Mpa mpa = mpaStorage.getMpaById(id);
        if (mpa == null) {
            throw new RuntimeException("Mpa не найдено");
        }
        return mpa;
    }

    public List<Mpa> getAllMpa() {
        return mpaStorage.getAllMpa();
    }
}
