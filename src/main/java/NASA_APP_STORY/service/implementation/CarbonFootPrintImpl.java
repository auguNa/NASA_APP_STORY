package NASA_APP_STORY.service.implementation;

import NASA_APP_STORY.entity.CarbonFootPrint;
import NASA_APP_STORY.repository.CarbonFootPrintRepository;
import NASA_APP_STORY.service.CarbonFootPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarbonFootPrintImpl implements CarbonFootPrintService {
    @Autowired
    private CarbonFootPrintRepository carbonFootPrintRepository;

    @Override
    public void logFootPrint(CarbonFootPrint carbonFootPrint) {
        carbonFootPrintRepository.save(carbonFootPrint);
    }
}
