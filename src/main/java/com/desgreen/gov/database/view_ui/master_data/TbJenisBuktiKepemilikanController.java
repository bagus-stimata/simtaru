package com.desgreen.gov.database.view_ui.master_data;

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
import java.util.List;
import java.util.stream.Collectors;

import com.desgreen.gov.database.SecurityConfig.PassEncoding;
import com.desgreen.gov.database.SecurityConfig.SecurityUtils;
import com.desgreen.gov.database.jpa_repository.TbJenisBuktiKepemilikanRepository;
import com.desgreen.gov.database.jpa_repository.TbKabupatenRepository;
import com.desgreen.gov.database.jpa_repository.TbKecamatanRepository;
import com.desgreen.gov.database.jpa_repository.TbJenisBuktiKepemilikanRepository;
import com.desgreen.gov.database.jpa_repository.TbJenisBuktiKepemilikanRepository;
import com.desgreen.gov.database.jpa_repository.UserRolesRepository;
import com.desgreen.gov.database.jpa_repository.UsersRepository;
import com.desgreen.gov.database.model.TbJenisBuktiKepemilikan;
import com.desgreen.gov.database.model.TbKecamatan;
import com.desgreen.gov.database.model.TbJenisBuktiKepemilikan;
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
public class TbJenisBuktiKepemilikanController {

    private static final Logger logger = LoggerFactory.getLogger(DinasController.class);

    @Autowired
    private TbJenisBuktiKepemilikanRepository tipeLayananRepository;
    
    @Autowired
    private SecurityUtils securityUtils;


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/master/jenisbuktikepemilikan")
    public String listIndex(final Model viewModel) {

        // final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

  
        final TbJenisBuktiKepemilikan newDomain =new TbJenisBuktiKepemilikan();
        viewModel.addAttribute("newDomain", newDomain);
        // viewModel.addAttribute("allTask", usersRepository.findByUserIdStatus(securityUtils.getLoginUser().getId(), Status.ACTIVE.getValue()));
        viewModel.addAttribute("allData", tipeLayananRepository.findAll());
        // viewModel.addAttribute("allData", new ArrayList<TbJenisBuktiKepemilikan>());

        logger.info("# Form Task");
      
        return "master/jenisbuktikepemilikan/jenisbuktikepemilikan_list"; 
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = {"/master/jenisbuktikepemilikan_form/save_process"}, method = RequestMethod.POST)
    public String saveProcess(@ModelAttribute("domain") final TbJenisBuktiKepemilikan domain, @ModelAttribute("domainDetil1") final TbJenisBuktiKepemilikan domainDetil1, final RedirectAttributes redirectAttributes) {
        if (domain.getTempInt1()==0) {
            TbJenisBuktiKepemilikan newDomain = new TbJenisBuktiKepemilikan();

            newDomain.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            newDomain.setKode1(domain.getKode1());
            newDomain.setDescription(domain.getDescription());

    
            if (tipeLayananRepository.save(newDomain) != null) {
                redirectAttributes.addFlashAttribute("saveUser", "success");
                newDomain.setTempInt1(1); //Karena langsung edit
            } else {
                redirectAttributes.addFlashAttribute("saveUser", "fail");
            }

    
            return "redirect:/master/jenisbuktikepemilikan/edit_form/" + newDomain.getId();

        }else if (domain.getTempInt1()==1) {
            TbJenisBuktiKepemilikan domainUpdate = tipeLayananRepository.findById(domain.getId() );
            domainUpdate.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            domainUpdate.setKode1(domain.getKode1());
            domainUpdate.setDescription(domain.getDescription());

            tipeLayananRepository.save(domainUpdate);

            redirectAttributes.addFlashAttribute("saveUser", "success");
            return "redirect:/master/jenisbuktikepemilikan/edit_form/" + domain.getId();
        }
       
        
        return "redirect:/master/jenisbuktikepemilikan/";
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/master/jenisbuktikepemilikan/{operation}/{id}", method = RequestMethod.GET)
    public String toFormOperation(@PathVariable("operation") final String operation,
                                @PathVariable("id") final int id, final RedirectAttributes redirectAttributes,
                                final Model model) {

        logger.info("/master/jenisbuktikepemilikan/operation: {} ", operation);

       if (operation.equals("delete")) {
            try {
                TbJenisBuktiKepemilikan domainToDelete = tipeLayananRepository.findById(id);        
                // hati-hati kecamatanRepository.deleteAll(domainToDelete.getListDesa() );
                tipeLayananRepository.delete(domainToDelete);               
                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", " Deleted permanently");
            } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("msg", "del_fail");
                    redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
                e.printStackTrace();
            }
           
        } else if (operation.equals("new_form")) {
            TbJenisBuktiKepemilikan newDomain = new TbJenisBuktiKepemilikan();
            if (newDomain != null) {
                newDomain.setTempInt1(0); //0.New Form, 1.Edit Form, 3.Delete
                model.addAttribute("domain", newDomain);                
                return "master/jenisbuktikepemilikan/jenisbuktikepemilikan_form";
            }

        } else if (operation.equals("edit_form")) {

            TbJenisBuktiKepemilikan domain = tipeLayananRepository.findById(id);
            if (domain != null) {
                domain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete
                //Password tidak bisa di decode ya?


                // TbJenisBuktiKepemilikanRoles domainDetil1 = new TbJenisBuktiKepemilikanRoles();
                // TbJenisBuktiKepemilikanRoles domainDetil2 = new TbJenisBuktiKepemilikanRoles();
                // // for (TbJenisBuktiKepemilikanRoles domainDetil: domain.getFuserRoles()) {
                // //     domainDetil1 = domainDetil; 
                // // }

                model.addAttribute("domain", domain);                

                // model.addAttribute("domainDetil1", domainDetil1);                
                // model.addAttribute("domainDetil2", domainDetil2);                

                // model.addAttribute("allDataDetil", domain.getFuserRoles());                

                return "master/jenisbuktikepemilikan/jenisbuktikepemilikan_form";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }

        return "redirect:/master/jenisbuktikepemilikan/";
    }

    

    @RequestMapping("/master/jenisbuktikepemilikan/perbaikan")
    @ResponseBody
    public String perbaikan(){
        String result = "Perbaikan desa error";
        return result;
    }

}
