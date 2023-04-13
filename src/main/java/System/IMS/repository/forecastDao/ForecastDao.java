package System.IMS.repository.forecastDao;

import System.IMS.entity.Forecast;

import java.util.List;

public interface ForecastDao {
    Forecast findById(Long id);

    List<Forecast> findAll();

    void save(Forecast forecast);

    void update(Forecast forecast);

    void delete(Forecast forecast);
}
