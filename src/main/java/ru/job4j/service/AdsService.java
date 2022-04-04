package ru.job4j.service;

import ru.job4j.model.Advertisement;
import ru.job4j.repository.AdsRepository;

import java.util.List;

public class AdsService {

    public AdsService() {
    }

    private static final class LazyAds {
        private static final AdsService INST = new AdsService();
    }

    public static AdsService instOf() {
        return LazyAds.INST;
    }

    public Advertisement addAd(Advertisement advertisement) {
        return AdsRepository.instOf().addAd(advertisement);
    }

    public List<Advertisement> findAllAds() {
        return AdsRepository.instOf().findAllAds();
    }

    public boolean updateStatusAd(int id) {
        return AdsRepository.instOf().updateStatusAd(id);
    }

    public boolean updatePhotoAd(int id) {
        return AdsRepository.instOf().updatePhotoAd(id);
    }

    public Advertisement findAdById(int id) {
        return AdsRepository.instOf().findAdById(id);
    }

}
