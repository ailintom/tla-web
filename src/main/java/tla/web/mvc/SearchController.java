package tla.web.mvc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tla.domain.command.LemmaSearch;
import tla.domain.model.Language;
import tla.domain.model.Script;
import tla.web.config.SearchProperties;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchProperties searchConfig;

    @ModelAttribute("allScripts")
    public Script[] getAllScripts() {
        return LemmaController.SEARCHABLE_SCRIPTS;
    }

    @ModelAttribute("allTranslationLanguages")
    public Language[] getAllTranslationLanguages() {
        return LemmaController.SEARCHABLE_TRANSLATION_LANGUAGES;
    }

    @ModelAttribute("wordClasses")
    public Map<String, List<String>> getWordClasses() {
        return searchConfig.getWordClasses();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String mainSearchPage(Model model) {
        model.addAttribute("lemmaSearchForm", new LemmaSearch());
        return "search";
    }

}