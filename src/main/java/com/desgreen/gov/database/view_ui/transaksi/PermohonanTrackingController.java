package com.desgreen.gov.database.view_ui.transaksi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.desgreen.gov.database.SecurityConfig.PassEncoding;
import com.desgreen.gov.database.SecurityConfig.SecurityUtils;
import com.desgreen.gov.database.application.AppSetting;
import com.desgreen.gov.database.jpa_repository.ChatsRepository;
import com.desgreen.gov.database.jpa_repository.TbArahanFungsiLahanRepository;
import com.desgreen.gov.database.jpa_repository.TbPermohonanRepository;
import com.desgreen.gov.database.jpa_repository.TbFungsiJalanRepository;
import com.desgreen.gov.database.jpa_repository.TbFungsiLahanSekitarRepository;
import com.desgreen.gov.database.jpa_repository.TbKabupatenRepository;
import com.desgreen.gov.database.jpa_repository.TbKecamatanRepository;
import com.desgreen.gov.database.jpa_repository.TbRencanaPemanfaatanRepository;
import com.desgreen.gov.database.jpa_repository.TbKlasifikasiKegiatanRepository;
import com.desgreen.gov.database.jpa_repository.TbPekerjaanRepository;
import com.desgreen.gov.database.jpa_repository.TbPelakuUsahaRepository;
import com.desgreen.gov.database.jpa_repository.TbPermohonanRepository;
import com.desgreen.gov.database.jpa_repository.TbStatusWilayahRepository;
import com.desgreen.gov.database.jpa_repository.TbTipeLayananRepository;
import com.desgreen.gov.database.jpa_repository.TbPermohonanRepository;
import com.desgreen.gov.database.jpa_repository.UserRolesRepository;
import com.desgreen.gov.database.jpa_repository.UsersRepository;
import com.desgreen.gov.database.model.TbPermohonan;
import com.desgreen.gov.database.model.TbKecamatan;
import com.desgreen.gov.database.model.TbPermohonan;
import com.desgreen.gov.database.model_enum.Role;


/**
 * The TodoController  Class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
@Controller
@ComponentScan
public class PermohonanTrackingController {

    private static final Logger logger = LoggerFactory.getLogger(PermohonanRekomController.class);

    @Autowired
    private TbTipeLayananRepository tipeLayananRepository;
    @Autowired
    private TbPelakuUsahaRepository pelakuUsahaRepository;
    @Autowired
    private TbRencanaPemanfaatanRepository kegiatanDimohonRepository;
    @Autowired
    private TbStatusWilayahRepository statusWilayahRepository;
    @Autowired
    private TbArahanFungsiLahanRepository arahanFungsiLahanRepository;
    @Autowired
    private TbFungsiLahanSekitarRepository fungsiLahanSekitarRepository;
    @Autowired
    private TbFungsiJalanRepository fungsiJalanRepository;
    @Autowired
    private TbKlasifikasiKegiatanRepository  klasifikasiKegiatanRepository;
    @Autowired
    private TbPekerjaanRepository  pekerjaanRepository;
    @Autowired
    private ChatsRepository  chatsRepository;
    @Autowired
    private TbKecamatanRepository kecamatanRepository;
    @Autowired
    private TbPermohonanRepository desaRepository;


    @Autowired
    private TbPermohonanRepository permohonanRepository;



    @Autowired
    private SecurityUtils securityUtils;


    private List<TbPermohonan> listDesa = new ArrayList<>();
    private List<TbKecamatan> listKecamatan = new ArrayList<>();

    
    String pageTitle = "";


    // @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/transaksi/permohonan_tracking/{id}")
    public String listIndex(@PathVariable("id") final long id,  Model viewModel) {

    

        TbPermohonan domainToTrack = permohonanRepository.findById(id);      
        // final TbPermohonan newDomain =new TbPermohonan();

        viewModel.addAttribute("domain", domainToTrack);
        // viewModel.addAttribute("allData", desaRepository.findAll());

        logger.info("# Form Task");

        pageTitle = AppSetting.APP_BRAND_NAME;
        viewModel.addAttribute("pageTitle", pageTitle);

        return "transaksi/permohonan_tracking/permohonan_tracking"; 
    }



 
    @RequestMapping("/transaksi/permohonan_tracking/perbaikan")
    @ResponseBody
    public String perbaikan(){
        String result = "Perbaikan desa error";        
        return result;
    }

}
