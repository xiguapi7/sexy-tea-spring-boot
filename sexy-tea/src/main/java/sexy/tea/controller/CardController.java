package sexy.tea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sexy.tea.model.Card;
import sexy.tea.model.common.Result;
import sexy.tea.service.CardService;

/**
 * @author 大大大西西瓜皮🍉
 * @date 16:53 2020-10-16
 * description:
 */
@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService service;

    @Autowired
    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        return service.selectByCardId(id);
    }

    @GetMapping("/selectByUid/{uid}")
    public Result selectByUid(@PathVariable Integer uid) {
        return service.selectByUid(uid);
    }

    @GetMapping("/selectByCardName/{cardName}")
    public Result selectByCardName(@PathVariable String cardName) {
        return service.selectByCardName(cardName);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Card card) {
        return service.createOrUpdate(card);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Card card) {
        return service.createOrUpdate(card);
    }
}
