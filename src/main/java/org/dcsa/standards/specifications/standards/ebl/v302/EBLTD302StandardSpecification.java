package org.dcsa.standards.specifications.standards.ebl.v302;

import java.util.List;
import java.util.stream.Stream;

import org.dcsa.standards.specifications.standards.ebl.v302.model.TransportDocument;

public class EBLTD302StandardSpecification extends EBL302StandardSpecification {

  public EBLTD302StandardSpecification() {
    super("Transport Document", "td");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    // add "feedbacks" to the TD object
    return Stream.concat(
        super.modelClassesStream()
            .filter(
                modelClass ->
                    !modelClass.equals(
                        org.dcsa.standards.specifications.standards.ebl.v302.model.TransportDocument
                            .class)),
        Stream.of(
            org.dcsa.standards.specifications.standards.ebl.v302.model_td.TransportDocument.class));
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(TransportDocument.class.getSimpleName());
  }
}
