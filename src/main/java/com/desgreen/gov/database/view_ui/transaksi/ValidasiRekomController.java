package com.desgreen.gov.database.view_ui.transaksi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;
import javax.management.Notification;

import com.desgreen.gov.database.SecurityConfig.PassEncoding;
import com.desgreen.gov.database.SecurityConfig.SecurityUtils;
import com.desgreen.gov.database.app_email.EmailService;
import com.desgreen.gov.database.app_email.EmailServiceImpl;
import com.desgreen.gov.database.application.AppSetting;
import com.desgreen.gov.database.jpa_repository.ChatsRepository;
import com.desgreen.gov.database.jpa_repository.TbArahanFungsiLahanRepository;
import com.desgreen.gov.database.jpa_repository.TbDesaRepository;
import com.desgreen.gov.database.jpa_repository.TbPermohonanRepository;
import com.desgreen.gov.database.jpa_repository.TbFungsiJalanRepository;
import com.desgreen.gov.database.jpa_repository.TbFungsiLahanSekitarRepository;
import com.desgreen.gov.database.jpa_repository.TbJenisBuktiKepemilikanRepository;
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
import com.desgreen.gov.database.model.TbRencanaPemanfaatan;
import com.desgreen.gov.database.model.TbStatusWilayah;
import com.desgreen.gov.database.model.TbTipeLayanan;
import com.desgreen.gov.database.model.FUserRoles;
import com.desgreen.gov.database.model.TbArahanFungsiLahan;
import com.desgreen.gov.database.model.TbDesa;
import com.desgreen.gov.database.model.TbFungsiJalan;
import com.desgreen.gov.database.model.TbFungsiLahanSekitar;
import com.desgreen.gov.database.model.TbJenisBuktiKepemilikan;
import com.desgreen.gov.database.model.TbKecamatan;
import com.desgreen.gov.database.model.TbKlasifikasiKegiatan;
import com.desgreen.gov.database.model.TbPermohonan;
import com.desgreen.gov.database.model_enum.Role;
import com.desgreen.gov.database.model_enum.StatusPersetujuan;


/**
 * The TodoController  Class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
@Controller
@ComponentScan
public class ValidasiRekomController {

    private static final Logger logger = LoggerFactory.getLogger(PermohonanRekomController.class);

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    private TbTipeLayananRepository tipeLayananRepository;
    @Autowired
    private TbPelakuUsahaRepository pelakuUsahaRepository;
    @Autowired
    private TbRencanaPemanfaatanRepository rencanaPemanfaatanRepository;

    @Autowired
    private TbArahanFungsiLahanRepository arahanFungsiLahanRepository;
    @Autowired
    private TbStatusWilayahRepository statusWilayahRepository;
    @Autowired
    private TbKlasifikasiKegiatanRepository  klasifikasiKegiatanRepository;

    @Autowired
    private TbFungsiLahanSekitarRepository fungsiLahanSekitarRepository;
    @Autowired
    private TbFungsiJalanRepository fungsiJalanRepository;

    @Autowired
    private TbPekerjaanRepository  pekerjaanRepository;
    @Autowired
    private ChatsRepository  chatsRepository;
    @Autowired
    private TbKecamatanRepository kecamatanRepository;
    @Autowired
    private TbDesaRepository desaRepository;
    @Autowired
    private TbJenisBuktiKepemilikanRepository jenisBuktiKepemilikanRepository;

    @Autowired
    private TbPermohonanRepository permohonanRepository;


    @Autowired
    private SecurityUtils securityUtils;


    private List<TbPermohonan> listDesa = new ArrayList<>();
    private List<TbKecamatan> listKecamatan = new ArrayList<>();

    
    String pageTitle = "";
    
    @ModelAttribute("getListTipeLayanan")
    public List<TbTipeLayanan> getListTipeLayanan() {
        List<TbTipeLayanan> list = new ArrayList<>();
        for (TbTipeLayanan domain: tipeLayananRepository.findAll() ) {
            list.add(domain); 
        }        
        return list;
    }
    @ModelAttribute("getListRencanaPemanfaatan")
    public List<TbRencanaPemanfaatan> getListRencanaPemanfaatan() {
        List<TbRencanaPemanfaatan> list = new ArrayList<>();
        for (TbRencanaPemanfaatan domain: rencanaPemanfaatanRepository.findAll() ) {
            list.add(domain); 
        }        
        return list;
    }

    @ModelAttribute("getListStatusWilayah")
    public List<TbStatusWilayah> getListStatusWilayah() {
        List<TbStatusWilayah> list = new ArrayList<>();
        for (TbStatusWilayah domain: statusWilayahRepository.findAll() ) {
            list.add(domain); 
        }        
        return list;
    }
    @ModelAttribute("getListArahanFungsiLahan")
    public List<TbArahanFungsiLahan> getListArahanFungsiLahan() {
        List<TbArahanFungsiLahan> list = new ArrayList<>();
        for (TbArahanFungsiLahan domain: arahanFungsiLahanRepository.findAll() ) {
            list.add(domain); 
        }        
        return list;
    }
    @ModelAttribute("getLisKlasifikasiKegiatan")
    public List<TbKlasifikasiKegiatan> getListKlasifikasiKegiatan() {
        List<TbKlasifikasiKegiatan> list = new ArrayList<>();
        for (TbKlasifikasiKegiatan domain: klasifikasiKegiatanRepository.findAll() ) {
            list.add(domain); 
        }        
        return list;
    }

    @ModelAttribute("getListFungsiLahanSekitar")
    public List<TbFungsiLahanSekitar> getListFungsiLahanSekitar() {
        List<TbFungsiLahanSekitar> list = new ArrayList<>();
        for (TbFungsiLahanSekitar domain: fungsiLahanSekitarRepository.findAll() ) {
            list.add(domain); 
        }        
        return list;
    }
    @ModelAttribute("getListFungsiJalan")
    public List<TbFungsiJalan> getListFungsiJalan() {
        List<TbFungsiJalan> list = new ArrayList<>();
        for (TbFungsiJalan domain: fungsiJalanRepository.findAll() ) {
            list.add(domain); 
        }        
        return list;
    }

    @ModelAttribute("getListKecamatan")
    public List<TbKecamatan> getListKecamatan() {
        List<TbKecamatan> list = new ArrayList<>();
        for (TbKecamatan domain: kecamatanRepository.findAll() ) {
            list.add(domain); 
        }        
        return list;
    }
    @ModelAttribute("getListDesa")
    public List<TbDesa> getListDesa() {
        List<TbDesa> list = new ArrayList<>();
        for (TbDesa domain: desaRepository.findAll() ) {
            list.add(domain); 
        }        
        return list;
    }
    @ModelAttribute("getListJenisBuktiKepemilikan")
    public List<TbJenisBuktiKepemilikan> getListJenisBuktiKepemilikan() {
        List<TbJenisBuktiKepemilikan> list = new ArrayList<>();
        for (TbJenisBuktiKepemilikan domain: jenisBuktiKepemilikanRepository.findAll() ) {
            list.add(domain); 
        }        
        return list;
    }

    @ModelAttribute("allStatusPersetujuan")
    public StatusPersetujuan[] getAllStatusPersetujuan() {
        return StatusPersetujuan.values();
    }
    @ModelAttribute("appImagePath")
    public String appImagePath(){
        return AppSetting.APP_IMAGE_PATH;
    }
   
    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN_1 + "', '" + Role.ADMIN_2 + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/transaksi/validasirekom")
    public String listIndex(final Model model) {

  
        final TbPermohonan newDomain =new TbPermohonan();
        model.addAttribute("newDomain", newDomain);

        for (FUserRoles test: securityUtils.getLoginUser().getFuserRoles()) {
            if (test.getRoleID().equals(Role.ADMIN_1)) {
                model.addAttribute("validasiKe1", 1);
            }
            if (test.getRoleID().equals(Role.ADMIN_2)) {
                model.addAttribute("validasiKe2", 1);
            }
        }


        List<TbPermohonan> list = new ArrayList<>();
        for (TbPermohonan domain: permohonanRepository.findAll()) {          
            try {
                if (domain.getStatusPersetujuan1().equals(StatusPersetujuan.VALID) && 
                    domain.getStatusPersetujuan2().equals(StatusPersetujuan.VALID)  && 
                    domain.getStatusPersetujuan3().equals(StatusPersetujuan.VALID) && 
                    domain.getStatusPersetujuan4().equals(StatusPersetujuan.VALID) ) {

                    domain.setStatusPersetujuanAll(StatusPersetujuan.VALID);

                } else if (domain.getStatusPersetujuan1().equals(StatusPersetujuan.INVALID) || 
                    domain.getStatusPersetujuan2().equals(StatusPersetujuan.INVALID)  || 
                    domain.getStatusPersetujuan3().equals(StatusPersetujuan.INVALID) || 
                    domain.getStatusPersetujuan4().equals(StatusPersetujuan.INVALID) ) {

                    domain.setStatusPersetujuanAll(StatusPersetujuan.INVALID);

                }else {
                    domain.setStatusPersetujuanAll(StatusPersetujuan.HOLD);
                }
            } catch (Exception e) {
            }

            list.add(domain);
        }
       
        model.addAttribute("allData",  list.stream().sorted(Comparator.comparing(TbPermohonan::getTanggalPermohonan).reversed()).collect(Collectors.toList()));

        logger.info("# Form Task");

        pageTitle = AppSetting.APP_BRAND_NAME;
        model.addAttribute("pageTitle", pageTitle);

        return "transaksi/validasirekom/validasirekom_list"; 
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN_1 + "', '" + Role.ADMIN_2 + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = {"/transaksi/validasirekom/save_process"}, method = RequestMethod.POST)
    public String saveProcess(@ModelAttribute("domain") final TbPermohonan domain,
                final RedirectAttributes redirectAttributes) {
    // public String saveProcess(@ModelAttribute("domain") final TbPermohonan domain,
    //          @RequestParam("file_1") MultipartFile file_1, @RequestParam("file_2") MultipartFile file_2, 
    //          @RequestParam("file_3") MultipartFile file_3, @RequestParam("file_4") MultipartFile file_4, 
    //          @RequestParam("file_5") MultipartFile file_5, @RequestParam("file_6") MultipartFile file_6, 
    //          @RequestParam("file_7") MultipartFile file_7, @RequestParam("file_8") MultipartFile file_8, 
    //             final RedirectAttributes redirectAttributes) {

        if (domain.getTempInt1()==0) {
            TbPermohonan newDomain = new TbPermohonan();
            // newDomain = getNewDomainFromView(newDomain, domain);
        
            newDomain.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete


            if (permohonanRepository.save(newDomain) != null) {                
                newDomain.setTempInt1(1);
               

                redirectAttributes.addFlashAttribute("saveUser", "success");
            } else {
                redirectAttributes.addFlashAttribute("saveUser", "fail");
            }

            return "redirect:/transaksi/validasirekom/edit_form/" + newDomain.getId();

        }else if (domain.getTempInt1()==1) {
            TbPermohonan domainUpdate = permohonanRepository.findById(domain.getId() );
            // domainUpdate = getNewDomainFromView(domainUpdate, domain);

            domainUpdate.setStatusWilayahBean(domain.getStatusWilayahBean());
            domainUpdate.setArahanFungsiLahanBean(domain.getArahanFungsiLahanBean());
            domainUpdate.setKlasifikasiKegiatanBean(domain.getKlasifikasiKegiatanBean());

            domainUpdate.setFungsiJalanBean(domain.getFungsiJalanBean());
            domainUpdate.setFungsiLahanSekitarBean(domain.getFungsiLahanSekitarBean());

            domainUpdate.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            domainUpdate.setStatusVerifikasi1(domain.getStatusVerifikasi1());
            domainUpdate.setStatusVerifikasi1Notes(domain.getStatusVerifikasi1Notes());
            //Sementara Verifikator sama saja
            domainUpdate.setStatusVerifikasi2(domain.getStatusVerifikasi2());
            domainUpdate.setStatusVerifikasi2Notes(domain.getStatusVerifikasi2Notes());
            
            domainUpdate.setStatusHarusRapat(domain.isStatusHarusRapat() );

            permohonanRepository.save(domainUpdate);


            /**
             * SEND EMAIL HERE
             */
            // String emailPemohon = domainUpdate.get

            String userEmail_Validator = "";
            String pemohonEmail ="";                    
        try {
                userEmail_Validator = securityUtils.getLoginUser().getEmail();
                pemohonEmail = domainUpdate.getFuserBean().getEmail();                    
            } catch (Exception e) {
            }
    
            String mailMessage = "Informasi dari " + AppSetting.APP_BRAND_NAME + " Bahwa Permohonan Rekomendasi Pemanfaatan Tata Ruang Anda " + 
            " Menadapat Response. Silahkan dilihat pada Aplikasi";

            try {
                emailService.sendSimpleMessage(userEmail_Validator, AppSetting.APP_BRAND_NAME,  mailMessage);
                
            } catch (Exception e) {
                //TODO: handle exception
            }
            try {
                emailService.sendSimpleMessage(pemohonEmail, AppSetting.APP_BRAND_NAME,  mailMessage);
                
            } catch (Exception e) {
            }
            
           
        
        
            redirectAttributes.addFlashAttribute("saveUser", "success");
            redirectAttributes.addFlashAttribute("saveUserInfo", domain.isStatusHarusRapat());
            
            return "redirect:/transaksi/validasirekom/edit_form/" + domainUpdate.getId();
        }
       
        
       

        return "redirect:/transaksi/validasirekom/";
    }

    

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN_1 + "', '" + Role.ADMIN_2 + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/transaksi/validasirekom/{operation}/{id}", method = RequestMethod.GET)
    public String toFormOperation(@PathVariable("operation") final String operation,
                                @PathVariable("id") final int id, final RedirectAttributes redirectAttributes,
                                final Model model) {

        logger.info("/transaksi/validasirekom/operation: {} ", operation);
        pageTitle = AppSetting.APP_BRAND_NAME;
        model.addAttribute("pageTitle", pageTitle);

       if (operation.equals("delete")) {
            try {
                TbPermohonan domainToDelete = permohonanRepository.findById(id);        
                // hati-hati kecamatanRepository.deleteAll(domainToDelete.getListDesa() );

                 try {
                    Path path = Paths.get(AppSetting.APP_IMAGE_PATH + domainToDelete.getImageSuratPermohonan());                    
                    Files.deleteIfExists(path);                         
                 } catch (Exception e) {}
                 try {
                    Path path = Paths.get(AppSetting.APP_IMAGE_PATH + domainToDelete.getImageKtp());
                    Files.deleteIfExists(path); 
                 } catch (Exception e) {}
                 try {
                    Path path = Paths.get(AppSetting.APP_IMAGE_PATH + domainToDelete.getImageKk());
                    Files.deleteIfExists(path);                         
                 } catch (Exception e) {}
                 try {
                    Path path = Paths.get(AppSetting.APP_IMAGE_PATH + domainToDelete.getImageBuktiKepemilikan());
                    Files.deleteIfExists(path);                         
                 } catch (Exception e) {}
                 try {
                    Path path = Paths.get(AppSetting.APP_IMAGE_PATH + domainToDelete.getImageSuratKeterangan());
                    Files.deleteIfExists(path);                         
                 } catch (Exception e) {}
                 try {
                    Path path = Paths.get(AppSetting.APP_IMAGE_PATH + domainToDelete.getImageAktePendirianPerusahaan());
                    Files.deleteIfExists(path);                         
                 } catch (Exception e) {}
                 try {
                    Path path = Paths.get(AppSetting.APP_IMAGE_PATH + domainToDelete.getImageBuktiPelunasanPbb());
                    Files.deleteIfExists(path);                         
                 } catch (Exception e) {}
                 try {
                    Path path = Paths.get(AppSetting.APP_IMAGE_PATH + domainToDelete.getImageHandDrawLokasi());
                    Files.deleteIfExists(path);                         
                 } catch (Exception e) {}

                permohonanRepository.delete(domainToDelete);       
                
                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", " Deleted permanently");
            } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("msg", "del_fail");
                    redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
                e.printStackTrace();
            }
           
        } else if (operation.equals("new_form")) {
            TbPermohonan newDomain = new TbPermohonan();
            if (newDomain != null) {
                newDomain.setTempInt1(0); //0.New Form, 1.Edit Form, 3.Delete


                model.addAttribute("domain", newDomain);      
                
                return "transaksi/validasirekom/validasirekom_form";
            }

        } else if (operation.equals("edit_form")) {

            TbPermohonan domain = permohonanRepository.findById(id);
            if (domain != null) {
                domain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete              
             
                for (FUserRoles test: securityUtils.getLoginUser().getFuserRoles()) {
                    if (test.getRoleID().equals(Role.ADMIN_1)) {
                        model.addAttribute("validasiKe1", 1);
                    }
                    if (test.getRoleID().equals(Role.ADMIN_2)) {
                        model.addAttribute("validasiKe2", 1);
                    }
                }

                model.addAttribute("domain", domain);           
                                            
                return "transaksi/validasirekom/validasirekom_form";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }

        return "redirect:/transaksi/validasirekom/";
    }

    

    @RequestMapping("/transaksi/validasirekom/perbaikan")
    @ResponseBody
    public String perbaikan(){
        String result = "Perbaikan desa error";        
        return result;
    }


    public TbPermohonan getNewDomainFromViewXX(TbPermohonan newDomain, TbPermohonan domain) {
        // TbPermohonan newDomain = new TbPermohonan();

        newDomain.setNoRek("");

        newDomain.setTipeLayananBean(domain.getTipeLayananBean());
        // newDomain.setStatusWilayahBean(domain.getStatusWilayahBean());
        newDomain.setRencanaPemanfaatanBean(domain.getRencanaPemanfaatanBean());
        // newDomain.setArahanFungsiLahanBean(domain.getArahanFungsiLahanBean());
        // newDomain.setFungsiLahanSekitarBean(domain.getFungsiLahanSekitarBean());
        // newDomain.setFungsiJalanBean(domain.getFungsiJalanBean());
        // newDomain.setKlasifikasiKegiatanBean(domain.getKlasifikasiKegiatanBean());

        newDomain.setJenisBuktiKepemilikanBean(domain.getJenisBuktiKepemilikanBean());
        newDomain.setNomorKepemilikan(domain.getNomorKepemilikan());

        // newDomain.setPekerjaanBean(domain.getPekerjaanBean());
        // newDomain.setPelakuUsahaBean(domain.getPelakuUsahaBean());

        newDomain.setAlamatDimohon(domain.getAlamatDimohon());
        newDomain.setDesaDimohonBean(domain.getDesaDimohonBean());
        newDomain.setDesaBean(domain.getDesaBean());
        // newDomain.setFuserBean(domain.getFuserBean()); //user tidak dari inputan
        newDomain.setNoKtpPemohon(domain.getNoKtpPemohon());
        newDomain.setAlamatPemohon(domain.getAlamatPemohon());

        // newDomain.setTanggalPermohonan(tanggalPermohonan);
       
        if (domain.getTanggalPermohonan()==null) newDomain.setTanggalPermohonan(LocalDate.now());

        newDomain.setLuasTanahBuktiKepemilikan(domain.getLuasTanahBuktiKepemilikan());
        newDomain.setLuasTanahDimohon(domain.getLuasTanahDimohon());
        newDomain.setLatDimohon(domain.getLatDimohon());
        newDomain.setLonDimohon(domain.getLonDimohon());
        
        /**
         * Block Image File akan dilakukan Penambaan Ulang
         */

         newDomain.setKeterangan(domain.getKeterangan());
         newDomain.setBatasTimur(domain.getBatasTimur());
         newDomain.setBatasSelatan(domain.getBatasSelatan());
         newDomain.setBatasUtara(domain.getBatasUtara());
         newDomain.setBatasBarat(domain.getBatasBarat());

         newDomain.setJarakFasumPendidikan(domain.getJarakFasumPendidikan());
         newDomain.setJarakFasumPerdagangan(domain.getJarakFasumPerdagangan());
         newDomain.setJarakFasumPerkantoran(domain.getJarakFasumPerkantoran());
        //  newDomain.setJarakFasumLain1(domain.getJarakFasumLain1());
        //  newDomain.setJarakFasumLain2(domain.getJarakFasumLain2());
         
         newDomain.setTelpPemohon(domain.getTelpPemohon());


        //  newDomain.setKonflikSosial(domain.isKonflikSosial());
        //  newDomain.setStatusVerifikasi1(domain.getStatusVerifikasi1());
        //  newDomain.setStatusVerifikasi1Notes(domain.getStatusVerifikasi1Notes());
        //  newDomain.setStatusVerifikasi2(domain.getStatusVerifikasi2());
        //  newDomain.setStatusVerifikasi2Notes(domain.getStatusVerifikasi2Notes());
        //  newDomain.setStatusVerifikasi1(domain.getStatusVerifikasi1());

        //  newDomain.setStatusHarusRapat(domain.isStatusHarusRapat() );

        //  newDomain.setStatusPersetujuan1(domain.getStatusPersetujuan1());
        //  newDomain.setStatusPersetujuan1Notes(domain.getStatusPersetujuan1Notes());
        //  newDomain.setStatusPersetujuan2(domain.getStatusPersetujuan2());
        //  newDomain.setStatusPersetujuan2Notes(domain.getStatusPersetujuan2Notes());
        //  newDomain.setStatusPersetujuan3(domain.getStatusPersetujuan3());
        //  newDomain.setStatusPersetujuan3Notes(domain.getStatusPersetujuan3Notes());
        //  newDomain.setStatusPersetujuan4(domain.getStatusPersetujuan4());
        //  newDomain.setStatusPersetujuan4Notes(domain.getStatusPersetujuan4Notes());

        return newDomain;
    }



	public boolean isValid_FormatEmail(String email) { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 


}
