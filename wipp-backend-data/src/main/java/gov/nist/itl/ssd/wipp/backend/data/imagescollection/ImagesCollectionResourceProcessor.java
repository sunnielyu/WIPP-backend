/*
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of
 * their official duties. Pursuant to title 17 Section 105 of the United
 * States Code this software is not subject to copyright protection and is
 * in the public domain. This software is an experimental system. NIST assumes
 * no responsibility whatsoever for its use by other parties, and makes no
 * guarantees, expressed or implied, about its quality, reliability, or
 * any other characteristic. We would appreciate acknowledgement if the
 * software is used.
 */
package gov.nist.itl.ssd.wipp.backend.data.imagescollection;

import gov.nist.itl.ssd.wipp.backend.core.rest.PaginationParameterTemplatesHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.stereotype.Component;

/**
 * Add link to images and metadata files to an imagesCollection
 *
 * @author Antoine Vandecreme <antoine.vandecreme at nist.gov>
 */
@Component
public class ImagesCollectionResourceProcessor
        implements RepresentationModelProcessor<EntityModel<ImagesCollection>> {

    @Autowired
    private PaginationParameterTemplatesHelper assembler;

    @Autowired
    private EntityLinks entityLinks;

    @Override
    public EntityModel<ImagesCollection> process(
            EntityModel<ImagesCollection> resource) {
        ImagesCollection imagesCollection = resource.getContent();
        Link imagesLink = entityLinks.linkForItemResource(
                ImagesCollection.class, imagesCollection.getId())
                .slash("images")
                .withRel("images");
        resource.add(assembler.appendPaginationParameterTemplates(imagesLink));

        Link metadataFileLink = entityLinks.linkForItemResource(
                ImagesCollection.class, imagesCollection.getId())
                .slash("metadataFiles")
                .withRel("metadataFiles");
        resource.add(assembler.appendPaginationParameterTemplates(
                metadataFileLink));

        Link downloadLink = linkTo(ImagesCollectionDownloadController.class,
                imagesCollection.getId())
        		.slash("request")
                .withRel("download");
        resource.add(downloadLink);

        Link copyLink = linkTo(ImagesCollectionCopyController.class,
                imagesCollection.getId())
                .withRel("copy");
        resource.add(copyLink);

        return resource;
    }

}
