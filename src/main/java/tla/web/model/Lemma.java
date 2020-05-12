package tla.web.model;

import java.util.List;
import java.util.SortedMap;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import tla.domain.model.Language;
import tla.domain.model.Script;
import tla.domain.model.extern.AttestedTimespan;
import tla.domain.model.meta.BTSeClass;

@Data
@SuperBuilder
@NoArgsConstructor
@BackendPath("lemma")
@BTSeClass("BTSLemmaEntry")
@EqualsAndHashCode(callSuper = true)
public class Lemma extends TLAObject {

    @Singular
    private SortedMap<Language, List<String>> translations;

    @Singular
    private List<Word> words;

    @Singular
    private List<AttestedTimespan> attestations;

    public Long getAttestationCount() {
        return this.attestations.stream().mapToLong(
            timespan -> timespan.getAttestations().getCount()
        ).sum();
    }

    /**
    * Determines the language phase this lemma belongs to
    */
    public Script getDictionaryName() {
        return Script.ofLemmaId(this.getId());
    }

}
