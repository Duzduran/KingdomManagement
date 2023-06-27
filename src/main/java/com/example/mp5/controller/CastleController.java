package com.example.mp5.controller;

import com.example.mp5.model.*;
import com.example.mp5.model.defenders.Archer;
import com.example.mp5.model.defenders.GateHolder;
import com.example.mp5.model.gardens.HolyGarden;
import com.example.mp5.model.gardens.PublicGarden;
import com.example.mp5.model.gardens.RoyalGarden;
import com.example.mp5.repository.RoyalGardenRepository;
import com.example.mp5.repository.PublicGardenRepository;
import com.example.mp5.repository.HolyGardenRepository;
import com.example.mp5.model.stables.Governor;
import com.example.mp5.model.stables.PrivateStable;
import com.example.mp5.model.stables.RoyalStable;
import com.example.mp5.model.stables.TraditionalStable;
import com.example.mp5.model.util.enums.Areas;
import com.example.mp5.model.util.enums.Religion;
import com.example.mp5.repository.*;
import com.example.mp5.service.CastleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller class for managing castle-related operations and views.
 */
@Controller
public class CastleController {

    private final ArcherRepository archerRepository;
    private final GateHolderRepository gateHolderRepository;
    private final BalistariaRepository balistariaRepository;
    private final CastleRepository castleRepository;
    private final StableRepository stableRepository;
    private final CastleService castleService;

    private final RoyalStableRepository royalStableRepository;
    private final TraditionalStableRepository traditionalStableRepository;
    private final ChapelRepository chapelRepository;
    private final GovernorRepository governorRepository;
    private final PrivateStableRepository privateStableRepository;
    private final HolyGardenRepository holyGardenRepository;
    private final PublicGardenRepository publicGardenRepository;
    private final RoyalGardenRepository royalGardenRepository;

    /**
     * Constructs a new instance of the CastleController.
     *
     * @param castleService              The castle service.
     * @param archerRepository           The archer repository.
     * @param gateHolderRepository       The gate holder repository.
     * @param balistariaRepository       The balistaria repository.
     * @param castleRepository           The castle repository.
     * @param stableRepository           The stable repository.
     * @param royalStableRepository      The royal stable repository.
     * @param traditionalStableRepository The traditional stable repository.
     * @param chapelRepository           The chapel repository.
     * @param governorRepository         The governor repository.
     * @param privateStableRepository    The private stable repository.
     * @param holyGardenRepository       The holy garden repository.
     * @param publicGardenRepository     The public garden repository.
     * @param royalGardenRepository      The royal garden repository.
     */
    @Autowired
    public CastleController(CastleService castleService, ArcherRepository archerRepository, GateHolderRepository gateHolderRepository, BalistariaRepository balistariaRepository,
                            CastleRepository castleRepository, StableRepository stableRepository, RoyalStableRepository royalStableRepository, TraditionalStableRepository traditionalStableRepository,
                            ChapelRepository chapelRepository,
                            GovernorRepository governorRepository,
                            PrivateStableRepository privateStableRepository,
                            HolyGardenRepository holyGardenRepository,
                            PublicGardenRepository publicGardenRepository,
                            RoyalGardenRepository royalGardenRepository) {
        this.archerRepository = archerRepository;
        this.gateHolderRepository = gateHolderRepository;
        this.balistariaRepository = balistariaRepository;
        this.castleRepository = castleRepository;
        this.castleService = castleService;
        this.stableRepository = stableRepository;
        this.royalStableRepository = royalStableRepository;
        this.traditionalStableRepository = traditionalStableRepository;
        this.chapelRepository = chapelRepository;
        this.governorRepository = governorRepository;
        this.privateStableRepository = privateStableRepository;
        this.holyGardenRepository = holyGardenRepository;
        this.publicGardenRepository = publicGardenRepository;
        this.royalGardenRepository = royalGardenRepository;
    }

    /**
     * Retrieves and displays the details of a castle.
     *
     * @param id    The ID of the castle.
     * @param model The model to be used for rendering the view.
     * @return The view name.
     * @throws JsonProcessingException If there is an error processing JSON.
     */
    @GetMapping("/castle")
    public String getCastle(@RequestParam Long id, Model model) throws JsonProcessingException {
        Castle castle = castleService.findById(id);
        //.orElseThrow(() -> new IllegalArgumentException("Invalid castle Id:" + id));
        model.addAttribute("castle", castle);
        List<Archer> archers = archerRepository.findByCastleId(id);
        model.addAttribute("archersJson", new ObjectMapper().writeValueAsString(archers));

        List<Balistaria> balistarias = balistariaRepository.findByCastleId(id);
        model.addAttribute("balistariasJson", new ObjectMapper().writeValueAsString(balistarias));

        List<Chapel> chapels = chapelRepository.findByCastleId(id);
        model.addAttribute("chapelsJson", new ObjectMapper().writeValueAsString(chapels));

        List<RoyalStable> barns = royalStableRepository.findByCastle_Id(id);
        model.addAttribute("barnsJson", new ObjectMapper().writeValueAsString(barns));

        List<TraditionalStable> traditionalStables = traditionalStableRepository.findByCastle_Id(id);
        model.addAttribute("traditionalStablesJson", new ObjectMapper().writeValueAsString(traditionalStables));


        List<HolyGarden> holyGarden = holyGardenRepository.findByCastleId(id);
        model.addAttribute("holyGardenJson", new ObjectMapper().writeValueAsString(traditionalStables));

        List<PublicGarden> publicGarden = publicGardenRepository.findByCastleId(id);
        model.addAttribute("publicGardenJson", new ObjectMapper().writeValueAsString(traditionalStables));

        List<RoyalGarden> royalGarden = royalGardenRepository.findByCastleId(id);
        model.addAttribute("royalGardenJson", new ObjectMapper().writeValueAsString(traditionalStables));

        List<GateHolder> gateHolders = gateHolderRepository.findByCastleId(id);
        model.addAttribute("gateHoldersJson", new ObjectMapper().writeValueAsString(gateHolders));

        List<PrivateStable> privateStables = privateStableRepository.findByCastleId(id);
        model.addAttribute("privateStablesJson", new ObjectMapper().writeValueAsString(privateStables));

        model.addAttribute("barn", new RoyalStable());
        model.addAttribute("traditionalStable", new TraditionalStable());
        model.addAttribute("areas", Areas.values());
        model.addAttribute("religions", Religion.values());
        model.addAttribute("privateStable", new PrivateStable());
        model.addAttribute("archer", new Archer());
        model.addAttribute("gateHolder", new GateHolder());
        model.addAttribute("balistaria", new Balistaria());
        model.addAttribute("chapel", new Chapel());
        model.addAttribute("royalGarden", new RoyalGarden());
        model.addAttribute("holyGarden", new HolyGarden());
        model.addAttribute("publicGarden", new PublicGarden());

        return "castle";
    }
    /**
     * Adds a new Balistaria to the castle.
     *
     * @param balistaria The Balistaria to be added.
     * @return The redirect URL.
     */
    @PostMapping("/addBalistaria")
    public String addBalistaria(@ModelAttribute Balistaria balistaria) {
        balistariaRepository.save(balistaria);
        return "redirect:/castle/" + balistaria.getBelongedPlace().getId();
    }
    /**
     * Adds a new Archer to the castle.
     *
     * @param archer    The Archer to be added.
     * @param castleId  The ID of the castle.
     * @return The redirect URL.
     */
    @PostMapping("/addArcher")
    public String addArcher(@ModelAttribute Archer archer, @RequestParam Long castleId) {
        // Retrieve the castle by its ID using the castleId parameter
        Castle castle = castleRepository.findById(castleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid castle Id: " + castleId));
        // Set the defends property of the Archer to the fetched Castle
        archer.setDefends(castle);
        // Save the Archer to the database
        archerRepository.save(archer);
        return "redirect:/castle?id=" + archer.getDefends().getId();
    }


    /**
     * Adds a new GateHolder to the castle.
     *
     * @param gateHolder The GateHolder to be added.
     * @param castleId   The ID of the castle.
     * @return The redirect URL.
     */
    @PostMapping("/addGateHolder")
    public String addGateHolder(@ModelAttribute GateHolder gateHolder,  @RequestParam Long castleId) {
        // Retrieve the castle by its ID using the castleId parameter
        Castle castle = castleRepository.findById(castleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid castle Id: " + castleId));
        // Set the defends property of the GateHolder to the fetched Castle
        gateHolder.setDefends(castle);

        // Save the GateHolder to the database
        gateHolderRepository.save(gateHolder);
        return "redirect:/castle?id=" + gateHolder.getDefends().getId();
}
    /**
     * Adds a new RoyalStable to the castle.
     *
     * @param castleId      The ID of the castle.
     * @param barn          The RoyalStable to be added.
     * @param governorName  The name of the governor.
     * @param governorAge   The age of the governor.
     * @param governorTitle The title of the governor.
     * @return The redirect URL.
     */
    @PostMapping("/addBarn")
    public String addRoyalStable(@RequestParam Long castleId, @ModelAttribute RoyalStable barn,
                                 @RequestParam("governorName") String governorName,
                                 @RequestParam("governorAge") int governorAge,
                                 @RequestParam("governorTitle") String governorTitle) {
        Castle castle = castleService.findById(castleId);
        if (castle == null) {
            // Handle the case where no Castle was found with the provided id
            return "redirect:/error";
        }

        // Add the royalStable to the castle's list of stables
        barn.setCastle(castle);

        Governor governor = Governor.builder()
                .name(governorName)
                .age(governorAge)
                .title(governorTitle)
                .build();
        barn.getGovernors().add(governor);

        governor.setGoverningStable(barn);

        royalStableRepository.save(barn);
        governorRepository.save(governor);


        return "redirect:/castle?id=" + castleId;
    }

    /**
     * Adds a new Chapel to the castle.
     *
     * @param castleId The ID of the castle.
     * @param chapel   The Chapel to be added.
     * @return The redirect URL.
     */
    @PostMapping("/addChapel")
    public String addChapel(@RequestParam Long castleId, @ModelAttribute Chapel chapel) {
        Castle castle = castleService.findById(castleId);
        if (castle == null) {
            // Handle the case where no Castle was found with the provided id
            return "redirect:/error";
        }

        // Add the royalStable to the castle's list of stables
        chapel.setBelongedCastle(castle);

        // Save the castle with the new royalStable to the database
        chapelRepository.save(chapel);

        return "redirect:/castle?id=" + castleId;
    }
    /**
     * Adds a new TraditionalStable to the castle.
     *
     * @param traditionalStable The TraditionalStable to be added.
     * @param castleId          The ID of the castle.
     * @return The redirect URL.
     */
    @PostMapping("/addTraditionalStable")
    public String addTraditionalStable(@ModelAttribute TraditionalStable traditionalStable, @RequestParam Long castleId) {
        // Retrieve the castle by its ID using the castleId parameter
        Castle castle = castleRepository.findById(castleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid castle Id: " + castleId));

        // Set the castle for the traditional stable
        traditionalStable.setCastle(castle);

        // Save the traditional stable to the database
        traditionalStableRepository.save(traditionalStable);

        return "redirect:/castle?id=" + castleId;
    }

    /**
     * Adds a new PrivateStable to the castle.
     *
     * @param privateStable The PrivateStable to be added.
     * @param castleId      The ID of the castle.
     * @return The redirect URL.
     */
    @PostMapping("/addPrivateStable")
    public String addPrivateStable(@ModelAttribute PrivateStable privateStable, @RequestParam Long castleId) {
        // Retrieve the castle by its ID using the castleId parameter
        Castle castle = castleRepository.findById(castleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid castle Id: " + castleId));

        // Set the castle for the traditional stable
        privateStable.setCastle(castle);

        // Save the traditional stable to the database
        stableRepository.save(privateStable);

        return "redirect:/castle?id=" + castleId;
    }
}