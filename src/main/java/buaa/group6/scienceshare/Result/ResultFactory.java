package buaa.group6.scienceshare.Result;

public class ResultFactory {
    /**
     * 通过data产生成功信息
     * @param data
     * @return Result
     */
    public static Result buildSuccessResult(Object data) {
        return buildResult(ResultCode.SUCCESS, "成功", data);
    }

    /**
     * 通过message产生成功信息
     * @param message
     * @return Result
     */
    public static Result buildSuccessResult(String message) {
        return buildResult(ResultCode.SUCCESS,  message,null);
    }

    /**
     * 通过message产生失败信息
     * @param message
     * @return Result
     */
    public static Result buildFailResult(String message) {
        return buildResult(ResultCode.FAIL, message, null);
    }

    /**
     * 通过resultCode产生失败信息
     * @param resultCode
     * @return Result
     */
    public static Result buildFailResult(ResultCode resultCode) {
        return buildResult(resultCode, "失败", null);
    }

    /**
     * 产生信息
     * @param resultCode
     * @param message
     * @param data
     * @return Result
     */
    public static Result buildResult(ResultCode resultCode, String message, Object data) {
        return buildResult(resultCode.code, message, data);
    }

    /**
     * 产生信息
     * @param resultCode
     * @param message
     * @return Result
     */
    public static Result buildResult(ResultCode resultCode, String message) {
        return buildResult(resultCode.code, message, null);
    }

    /**
     * 产生信息
     * @param resultCode
     * @param message
     * @param data
     * @return Result
     */
    public static Result buildResult(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }
}
