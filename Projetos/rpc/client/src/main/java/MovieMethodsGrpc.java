import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Serviço que define os métodos para operações de CRUD e buscas
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.23.0)",
    comments = "Source: MoviesRPC.proto")
public final class MovieMethodsGrpc {

  private MovieMethodsGrpc() {}

  public static final String SERVICE_NAME = "MovieMethods";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<MoviesRPC.Movie,
      MoviesRPC.Response> getCreateMovieMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateMovie",
      requestType = MoviesRPC.Movie.class,
      responseType = MoviesRPC.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MoviesRPC.Movie,
      MoviesRPC.Response> getCreateMovieMethod() {
    io.grpc.MethodDescriptor<MoviesRPC.Movie, MoviesRPC.Response> getCreateMovieMethod;
    if ((getCreateMovieMethod = MovieMethodsGrpc.getCreateMovieMethod) == null) {
      synchronized (MovieMethodsGrpc.class) {
        if ((getCreateMovieMethod = MovieMethodsGrpc.getCreateMovieMethod) == null) {
          MovieMethodsGrpc.getCreateMovieMethod = getCreateMovieMethod =
              io.grpc.MethodDescriptor.<MoviesRPC.Movie, MoviesRPC.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateMovie"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MoviesRPC.Movie.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MoviesRPC.Response.getDefaultInstance()))
              .setSchemaDescriptor(new MovieMethodsMethodDescriptorSupplier("CreateMovie"))
              .build();
        }
      }
    }
    return getCreateMovieMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MoviesRPC.MovieName,
      MoviesRPC.Response> getGetMovieMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetMovie",
      requestType = MoviesRPC.MovieName.class,
      responseType = MoviesRPC.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MoviesRPC.MovieName,
      MoviesRPC.Response> getGetMovieMethod() {
    io.grpc.MethodDescriptor<MoviesRPC.MovieName, MoviesRPC.Response> getGetMovieMethod;
    if ((getGetMovieMethod = MovieMethodsGrpc.getGetMovieMethod) == null) {
      synchronized (MovieMethodsGrpc.class) {
        if ((getGetMovieMethod = MovieMethodsGrpc.getGetMovieMethod) == null) {
          MovieMethodsGrpc.getGetMovieMethod = getGetMovieMethod =
              io.grpc.MethodDescriptor.<MoviesRPC.MovieName, MoviesRPC.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetMovie"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MoviesRPC.MovieName.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MoviesRPC.Response.getDefaultInstance()))
              .setSchemaDescriptor(new MovieMethodsMethodDescriptorSupplier("GetMovie"))
              .build();
        }
      }
    }
    return getGetMovieMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MoviesRPC.MovieFilters,
      MoviesRPC.Response> getGetMoviesByActorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetMoviesByActor",
      requestType = MoviesRPC.MovieFilters.class,
      responseType = MoviesRPC.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MoviesRPC.MovieFilters,
      MoviesRPC.Response> getGetMoviesByActorMethod() {
    io.grpc.MethodDescriptor<MoviesRPC.MovieFilters, MoviesRPC.Response> getGetMoviesByActorMethod;
    if ((getGetMoviesByActorMethod = MovieMethodsGrpc.getGetMoviesByActorMethod) == null) {
      synchronized (MovieMethodsGrpc.class) {
        if ((getGetMoviesByActorMethod = MovieMethodsGrpc.getGetMoviesByActorMethod) == null) {
          MovieMethodsGrpc.getGetMoviesByActorMethod = getGetMoviesByActorMethod =
              io.grpc.MethodDescriptor.<MoviesRPC.MovieFilters, MoviesRPC.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetMoviesByActor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MoviesRPC.MovieFilters.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MoviesRPC.Response.getDefaultInstance()))
              .setSchemaDescriptor(new MovieMethodsMethodDescriptorSupplier("GetMoviesByActor"))
              .build();
        }
      }
    }
    return getGetMoviesByActorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MoviesRPC.MovieFilters,
      MoviesRPC.Response> getGetMoviesByCategoryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetMoviesByCategory",
      requestType = MoviesRPC.MovieFilters.class,
      responseType = MoviesRPC.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MoviesRPC.MovieFilters,
      MoviesRPC.Response> getGetMoviesByCategoryMethod() {
    io.grpc.MethodDescriptor<MoviesRPC.MovieFilters, MoviesRPC.Response> getGetMoviesByCategoryMethod;
    if ((getGetMoviesByCategoryMethod = MovieMethodsGrpc.getGetMoviesByCategoryMethod) == null) {
      synchronized (MovieMethodsGrpc.class) {
        if ((getGetMoviesByCategoryMethod = MovieMethodsGrpc.getGetMoviesByCategoryMethod) == null) {
          MovieMethodsGrpc.getGetMoviesByCategoryMethod = getGetMoviesByCategoryMethod =
              io.grpc.MethodDescriptor.<MoviesRPC.MovieFilters, MoviesRPC.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetMoviesByCategory"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MoviesRPC.MovieFilters.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MoviesRPC.Response.getDefaultInstance()))
              .setSchemaDescriptor(new MovieMethodsMethodDescriptorSupplier("GetMoviesByCategory"))
              .build();
        }
      }
    }
    return getGetMoviesByCategoryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MoviesRPC.Movie,
      MoviesRPC.Response> getUpdateMovieMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateMovie",
      requestType = MoviesRPC.Movie.class,
      responseType = MoviesRPC.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MoviesRPC.Movie,
      MoviesRPC.Response> getUpdateMovieMethod() {
    io.grpc.MethodDescriptor<MoviesRPC.Movie, MoviesRPC.Response> getUpdateMovieMethod;
    if ((getUpdateMovieMethod = MovieMethodsGrpc.getUpdateMovieMethod) == null) {
      synchronized (MovieMethodsGrpc.class) {
        if ((getUpdateMovieMethod = MovieMethodsGrpc.getUpdateMovieMethod) == null) {
          MovieMethodsGrpc.getUpdateMovieMethod = getUpdateMovieMethod =
              io.grpc.MethodDescriptor.<MoviesRPC.Movie, MoviesRPC.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateMovie"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MoviesRPC.Movie.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MoviesRPC.Response.getDefaultInstance()))
              .setSchemaDescriptor(new MovieMethodsMethodDescriptorSupplier("UpdateMovie"))
              .build();
        }
      }
    }
    return getUpdateMovieMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MoviesRPC.MovieName,
      MoviesRPC.Response> getDeleteMovieMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteMovie",
      requestType = MoviesRPC.MovieName.class,
      responseType = MoviesRPC.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MoviesRPC.MovieName,
      MoviesRPC.Response> getDeleteMovieMethod() {
    io.grpc.MethodDescriptor<MoviesRPC.MovieName, MoviesRPC.Response> getDeleteMovieMethod;
    if ((getDeleteMovieMethod = MovieMethodsGrpc.getDeleteMovieMethod) == null) {
      synchronized (MovieMethodsGrpc.class) {
        if ((getDeleteMovieMethod = MovieMethodsGrpc.getDeleteMovieMethod) == null) {
          MovieMethodsGrpc.getDeleteMovieMethod = getDeleteMovieMethod =
              io.grpc.MethodDescriptor.<MoviesRPC.MovieName, MoviesRPC.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteMovie"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MoviesRPC.MovieName.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MoviesRPC.Response.getDefaultInstance()))
              .setSchemaDescriptor(new MovieMethodsMethodDescriptorSupplier("DeleteMovie"))
              .build();
        }
      }
    }
    return getDeleteMovieMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MovieMethodsStub newStub(io.grpc.Channel channel) {
    return new MovieMethodsStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MovieMethodsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MovieMethodsBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MovieMethodsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MovieMethodsFutureStub(channel);
  }

  /**
   * <pre>
   * Serviço que define os métodos para operações de CRUD e buscas
   * </pre>
   */
  public static abstract class MovieMethodsImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Create
     * </pre>
     */
    public void createMovie(MoviesRPC.Movie request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateMovieMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieve (by ID or Name)
     * </pre>
     */
    public void getMovie(MoviesRPC.MovieName request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMovieMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieve by Actor
     * </pre>
     */
    public void getMoviesByActor(MoviesRPC.MovieFilters request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMoviesByActorMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieve by Category (Genre)
     * </pre>
     */
    public void getMoviesByCategory(MoviesRPC.MovieFilters request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMoviesByCategoryMethod(), responseObserver);
    }

    /**
     * <pre>
     * Update
     * </pre>
     */
    public void updateMovie(MoviesRPC.Movie request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateMovieMethod(), responseObserver);
    }

    /**
     * <pre>
     * Delete
     * </pre>
     */
    public void deleteMovie(MoviesRPC.MovieName request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteMovieMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateMovieMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                MoviesRPC.Movie,
                MoviesRPC.Response>(
                  this, METHODID_CREATE_MOVIE)))
          .addMethod(
            getGetMovieMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                MoviesRPC.MovieName,
                MoviesRPC.Response>(
                  this, METHODID_GET_MOVIE)))
          .addMethod(
            getGetMoviesByActorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                MoviesRPC.MovieFilters,
                MoviesRPC.Response>(
                  this, METHODID_GET_MOVIES_BY_ACTOR)))
          .addMethod(
            getGetMoviesByCategoryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                MoviesRPC.MovieFilters,
                MoviesRPC.Response>(
                  this, METHODID_GET_MOVIES_BY_CATEGORY)))
          .addMethod(
            getUpdateMovieMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                MoviesRPC.Movie,
                MoviesRPC.Response>(
                  this, METHODID_UPDATE_MOVIE)))
          .addMethod(
            getDeleteMovieMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                MoviesRPC.MovieName,
                MoviesRPC.Response>(
                  this, METHODID_DELETE_MOVIE)))
          .build();
    }
  }

  /**
   * <pre>
   * Serviço que define os métodos para operações de CRUD e buscas
   * </pre>
   */
  public static final class MovieMethodsStub extends io.grpc.stub.AbstractStub<MovieMethodsStub> {
    private MovieMethodsStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MovieMethodsStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MovieMethodsStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MovieMethodsStub(channel, callOptions);
    }

    /**
     * <pre>
     * Create
     * </pre>
     */
    public void createMovie(MoviesRPC.Movie request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateMovieMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieve (by ID or Name)
     * </pre>
     */
    public void getMovie(MoviesRPC.MovieName request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMovieMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieve by Actor
     * </pre>
     */
    public void getMoviesByActor(MoviesRPC.MovieFilters request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMoviesByActorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieve by Category (Genre)
     * </pre>
     */
    public void getMoviesByCategory(MoviesRPC.MovieFilters request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMoviesByCategoryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Update
     * </pre>
     */
    public void updateMovie(MoviesRPC.Movie request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateMovieMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Delete
     * </pre>
     */
    public void deleteMovie(MoviesRPC.MovieName request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteMovieMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Serviço que define os métodos para operações de CRUD e buscas
   * </pre>
   */
  public static final class MovieMethodsBlockingStub extends io.grpc.stub.AbstractStub<MovieMethodsBlockingStub> {
    private MovieMethodsBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MovieMethodsBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MovieMethodsBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MovieMethodsBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Create
     * </pre>
     */
    public MoviesRPC.Response createMovie(MoviesRPC.Movie request) {
      return blockingUnaryCall(
          getChannel(), getCreateMovieMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieve (by ID or Name)
     * </pre>
     */
    public MoviesRPC.Response getMovie(MoviesRPC.MovieName request) {
      return blockingUnaryCall(
          getChannel(), getGetMovieMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieve by Actor
     * </pre>
     */
    public MoviesRPC.Response getMoviesByActor(MoviesRPC.MovieFilters request) {
      return blockingUnaryCall(
          getChannel(), getGetMoviesByActorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieve by Category (Genre)
     * </pre>
     */
    public MoviesRPC.Response getMoviesByCategory(MoviesRPC.MovieFilters request) {
      return blockingUnaryCall(
          getChannel(), getGetMoviesByCategoryMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Update
     * </pre>
     */
    public MoviesRPC.Response updateMovie(MoviesRPC.Movie request) {
      return blockingUnaryCall(
          getChannel(), getUpdateMovieMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Delete
     * </pre>
     */
    public MoviesRPC.Response deleteMovie(MoviesRPC.MovieName request) {
      return blockingUnaryCall(
          getChannel(), getDeleteMovieMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Serviço que define os métodos para operações de CRUD e buscas
   * </pre>
   */
  public static final class MovieMethodsFutureStub extends io.grpc.stub.AbstractStub<MovieMethodsFutureStub> {
    private MovieMethodsFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MovieMethodsFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MovieMethodsFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MovieMethodsFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Create
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<MoviesRPC.Response> createMovie(
        MoviesRPC.Movie request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateMovieMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieve (by ID or Name)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<MoviesRPC.Response> getMovie(
        MoviesRPC.MovieName request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMovieMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieve by Actor
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<MoviesRPC.Response> getMoviesByActor(
        MoviesRPC.MovieFilters request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMoviesByActorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieve by Category (Genre)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<MoviesRPC.Response> getMoviesByCategory(
        MoviesRPC.MovieFilters request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMoviesByCategoryMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Update
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<MoviesRPC.Response> updateMovie(
        MoviesRPC.Movie request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateMovieMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Delete
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<MoviesRPC.Response> deleteMovie(
        MoviesRPC.MovieName request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteMovieMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_MOVIE = 0;
  private static final int METHODID_GET_MOVIE = 1;
  private static final int METHODID_GET_MOVIES_BY_ACTOR = 2;
  private static final int METHODID_GET_MOVIES_BY_CATEGORY = 3;
  private static final int METHODID_UPDATE_MOVIE = 4;
  private static final int METHODID_DELETE_MOVIE = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MovieMethodsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MovieMethodsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_MOVIE:
          serviceImpl.createMovie((MoviesRPC.Movie) request,
              (io.grpc.stub.StreamObserver<MoviesRPC.Response>) responseObserver);
          break;
        case METHODID_GET_MOVIE:
          serviceImpl.getMovie((MoviesRPC.MovieName) request,
              (io.grpc.stub.StreamObserver<MoviesRPC.Response>) responseObserver);
          break;
        case METHODID_GET_MOVIES_BY_ACTOR:
          serviceImpl.getMoviesByActor((MoviesRPC.MovieFilters) request,
              (io.grpc.stub.StreamObserver<MoviesRPC.Response>) responseObserver);
          break;
        case METHODID_GET_MOVIES_BY_CATEGORY:
          serviceImpl.getMoviesByCategory((MoviesRPC.MovieFilters) request,
              (io.grpc.stub.StreamObserver<MoviesRPC.Response>) responseObserver);
          break;
        case METHODID_UPDATE_MOVIE:
          serviceImpl.updateMovie((MoviesRPC.Movie) request,
              (io.grpc.stub.StreamObserver<MoviesRPC.Response>) responseObserver);
          break;
        case METHODID_DELETE_MOVIE:
          serviceImpl.deleteMovie((MoviesRPC.MovieName) request,
              (io.grpc.stub.StreamObserver<MoviesRPC.Response>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MovieMethodsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MovieMethodsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return MoviesRPC.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MovieMethods");
    }
  }

  private static final class MovieMethodsFileDescriptorSupplier
      extends MovieMethodsBaseDescriptorSupplier {
    MovieMethodsFileDescriptorSupplier() {}
  }

  private static final class MovieMethodsMethodDescriptorSupplier
      extends MovieMethodsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MovieMethodsMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MovieMethodsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MovieMethodsFileDescriptorSupplier())
              .addMethod(getCreateMovieMethod())
              .addMethod(getGetMovieMethod())
              .addMethod(getGetMoviesByActorMethod())
              .addMethod(getGetMoviesByCategoryMethod())
              .addMethod(getUpdateMovieMethod())
              .addMethod(getDeleteMovieMethod())
              .build();
        }
      }
    }
    return result;
  }
}
