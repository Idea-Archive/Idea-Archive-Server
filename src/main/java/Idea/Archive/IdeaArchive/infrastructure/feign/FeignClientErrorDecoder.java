package Idea.Archive.IdeaArchive.infrastructure.feign;

import Idea.Archive.IdeaArchive.infrastructure.feign.exception.BadRequestException;
import Idea.Archive.IdeaArchive.infrastructure.feign.exception.ExpiredTokenException;
import Idea.Archive.IdeaArchive.infrastructure.feign.exception.ForbiddenException;
import Idea.Archive.IdeaArchive.infrastructure.feign.exception.UnAuthorizedException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
public class FeignClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        if(response.status() >= 400) {
            switch (response.status()) {
                case 401:
                    throw new UnAuthorizedException("권한이 없습니다. 401");
                case 403:
                    throw new ForbiddenException("권한이 없습니다. 403");
                case 419:
                    throw new ExpiredTokenException("토큰이 만료되었습니다.");
                default:
                    throw new BadRequestException("잘못된 요청입니다.");
            }
        }

        return FeignException.errorStatus(methodKey, response);
    }
}
