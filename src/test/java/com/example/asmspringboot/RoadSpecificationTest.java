package com.example.asmspringboot;

import com.example.asmspringboot.config.H2JpaConfig;
import com.example.asmspringboot.entity.District;
import com.example.asmspringboot.entity.Road;
import com.example.asmspringboot.repository.DistrictRepository;
import com.example.asmspringboot.repository.RoadRepository;
import com.example.asmspringboot.specification.RoadSpecification;
import com.example.asmspringboot.specification.SearchCriteria;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AsmSpringBootApplication.class, H2JpaConfig.class})
@ActiveProfiles("test")
public class RoadSpecificationTest {
    @Autowired
    RoadRepository roadRepository;
    @Autowired
    DistrictRepository districtRepository;

    private Road tonThatThuyet;
    private Road phamVanBach;
    private District cauGiay;
    private District namTuLiem;

    @Before
    public void init() {
        cauGiay = new District();
        cauGiay.setName("Cầu Giấy");
        districtRepository.save(cauGiay);
        namTuLiem = new District();
        namTuLiem.setName("Nam Từ Liêm");
        districtRepository.save(namTuLiem );

        tonThatThuyet = new Road();
        tonThatThuyet.setName("John");
        tonThatThuyet.setDistrict(cauGiay);
        tonThatThuyet.setDescription("tonthatthuyet");
        roadRepository.save(tonThatThuyet);

        phamVanBach = new Road();
        phamVanBach.setName("Phạm Văn Bạch");
        phamVanBach.setDistrict(namTuLiem);
        phamVanBach.setDescription("nam tu liem");
        roadRepository.save(phamVanBach);
    }

    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        RoadSpecification spec = new RoadSpecification(new SearchCriteria("name", "%", "Tôn Thất Thuyết"));
        List<Road> results = roadRepository.findAll(spec);
        System.out.println("Dữ liệu trả về: ");
                for (Road od :
                results) {
            System.out.println(od.getName());
        }
    }
}
