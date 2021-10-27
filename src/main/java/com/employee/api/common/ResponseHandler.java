package com.employee.api.common;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;


public class ResponseHandler {

    private static final Logger LOGGER = Logger.getLogger(ResponseHandler.class);

    @SuppressWarnings({"rawtypes"})
    public static ResponseGenericDTO responseSuccessful(Object data) {
        if (data != null) {
            if (data instanceof Page) {
                Page page = ((Page) data);
                return instanceOfPage(page);
            } else if (data instanceof Collection && ((Collection) data).isEmpty()) {

                return new ResponseGenericDTO<>(new ResponseMessage<>("204", "Record not Found"));
            } else {

                return new ResponseGenericDTO<>(data);
            }
        } else {

            return new ResponseGenericDTO<>(new ResponseMessage<>("204", "Record not Found"));
        }
    }

   

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static ResponseGenericDTO responseWithError(ResponseMessage error, String stackTrace) {
        LOGGER.error("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        LOGGER.error(stackTrace);
        LOGGER.error("GlamUp Exception Occurred : " + stackTrace);
        return new ResponseGenericDTO(error);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static ResponseGenericDTO responseWithError(ResponseMessage error) {
        return new ResponseGenericDTO(error);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static ResponseGenericDTO responseWithError(String errorMsg) {
        return new ResponseGenericDTO(new ResponseMessage<>("500", errorMsg));
    }

    @SuppressWarnings("rawtypes")
    public static ResponseGenericDTO responseSuccessful() {

        return new ResponseGenericDTO<>(new ResponseMessage<String>("200", "SUCCESS"));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static ResponseGenericDTO instanceOfPage(Page page) {

        PaginationDTO paginationDTO;
        if (page.getContent().isEmpty()) {
            return new ResponseGenericDTO(new ResponseMessage<>("204", "Record not Found"));
        } else if (page.getSort().isSorted()) {
            paginationDTO = sortedPage(page);
        } else {
            paginationDTO = unsortedPage(page);
        }

        return new ResponseGenericDTO<>(page.getContent(), paginationDTO);
    }

    @SuppressWarnings("rawtypes")
    private static PaginationDTO sortedPage(Page page) {

        Iterator<Sort.Order> iterator = page.getSort().iterator();
        String sortBy = null;
        String direction = null;

        while (iterator.hasNext()) {
            Sort.Order order = iterator.next();
            sortBy = order.getProperty();
            direction = order.getDirection().name();
        }
        return new PaginationDTO(page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements(), sortBy, direction);
    }

    @SuppressWarnings("rawtypes")
    private static PaginationDTO unsortedPage(Page page) {
        return new PaginationDTO(page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements());
    }

}
