import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Serviço que define os métodos para operações de CRUD e buscas
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: MoviesRPC.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MovieMethodsGrpc {

  private MovieMethodsGrpc() {}

  public static final java.lang.String SERVICE_NAME = "MovieMethods";

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
    io.grpc.stub.AbstractStub.StubFactory<MovieMethodsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MovieMethodsStub>() {
        @java.lang.Override
        public MovieMethodsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MovieMethodsStub(channel, callOptions);
        }
      };
    return MovieMethodsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MovieMethodsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MovieMethodsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MovieMethodsBlockingStub>() {
        @java.lang.Override
        public MovieMethodsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MovieMethodsBlockingStub(channel, callOptions);
        }
      };
    return MovieMethodsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MovieMethodsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MovieMethodsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MovieMethodsFutureStub>() {
        @java.lang.Override
        public MovieMethodsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MovieMethodsFutureStub(channel, callOptions);
        }
      };
    return MovieMethodsFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Serviço que define os métodos para operações de CRUD e buscas
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Create
     * </pre>
     */
    default void createMovie(MoviesRPC.Movie request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateMovieMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieve (by ID or Name)
     * </pre>
     */
    default void getMovie(MoviesRPC.MovieName request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMovieMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieve by Actor
     * </pre>
     */
    default void getMoviesByActor(MoviesRPC.MovieFilters request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMoviesByActorMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieve by Category (Genre)
     * </pre>
     */
    default void getMoviesByCategory(MoviesRPC.MovieFilters request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMoviesByCategoryMethod(), responseObserver);
    }

    /**
     * <pre>
     * Update
     * </pre>
     */
    default void updateMovie(MoviesRPC.Movie request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateMovieMethod(), responseObserver);
    }

    /**
     * <pre>
     * Delete
     * </pre>
     */
    default void deleteMovie(MoviesRPC.MovieName request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteMovieMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service MovieMethods.
   * <pre>
   * Serviço que define os métodos para operações de CRUD e buscas
   * </pre>
   */
  public static abstract class MovieMethodsImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MovieMethodsGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service MovieMethods.
   * <pre>
   * Serviço que define os métodos para operações de CRUD e buscas
   * </pre>
   */
  public static final class MovieMethodsStub
      extends io.grpc.stub.AbstractAsyncStub<MovieMethodsStub> {
    private MovieMethodsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MovieMethodsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MovieMethodsStub(channel, callOptions);
    }

    /**
     * <pre>
     * Create
     * </pre>
     */
    public void createMovie(MoviesRPC.Movie request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateMovieMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieve (by ID or Name)
     * </pre>
     */
    public void getMovie(MoviesRPC.MovieName request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMovieMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieve by Actor
     * </pre>
     */
    public void getMoviesByActor(MoviesRPC.MovieFilters request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMoviesByActorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieve by Category (Genre)
     * </pre>
     */
    public void getMoviesByCategory(MoviesRPC.MovieFilters request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMoviesByCategoryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Update
     * </pre>
     */
    public void updateMovie(MoviesRPC.Movie request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateMovieMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Delete
     * </pre>
     */
    public void deleteMovie(MoviesRPC.MovieName request,
        io.grpc.stub.StreamObserver<MoviesRPC.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteMovieMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service MovieMethods.
   * <pre>
   * Serviço que define os métodos para operações de CRUD e buscas
   * </pre>
   */
  public static final class MovieMethodsBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MovieMethodsBlockingStub> {
    private MovieMethodsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MovieMethodsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MovieMethodsBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Create
     * </pre>
     */
    public MoviesRPC.Response createMovie(MoviesRPC.Movie request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateMovieMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieve (by ID or Name)
     * </pre>
     */
    public MoviesRPC.Response getMovie(MoviesRPC.MovieName request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMovieMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieve by Actor
     * </pre>
     */
    public MoviesRPC.Response getMoviesByActor(MoviesRPC.MovieFilters request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMoviesByActorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieve by Category (Genre)
     * </pre>
     */
    public MoviesRPC.Response getMoviesByCategory(MoviesRPC.MovieFilters request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMoviesByCategoryMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Update
     * </pre>
     */
    public MoviesRPC.Response updateMovie(MoviesRPC.Movie request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateMovieMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Delete
     * </pre>
     */
    public MoviesRPC.Response deleteMovie(MoviesRPC.MovieName request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteMovieMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service MovieMethods.
   * <pre>
   * Serviço que define os métodos para operações de CRUD e buscas
   * </pre>
   */
  public static final class MovieMethodsFutureStub
      extends io.grpc.stub.AbstractFutureStub<MovieMethodsFutureStub> {
    private MovieMethodsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MovieMethodsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MovieMethodsFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Create
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<MoviesRPC.Response> createMovie(
        MoviesRPC.Movie request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateMovieMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieve (by ID or Name)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<MoviesRPC.Response> getMovie(
        MoviesRPC.MovieName request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMovieMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieve by Actor
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<MoviesRPC.Response> getMoviesByActor(
        MoviesRPC.MovieFilters request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMoviesByActorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieve by Category (Genre)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<MoviesRPC.Response> getMoviesByCategory(
        MoviesRPC.MovieFilters request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMoviesByCategoryMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Update
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<MoviesRPC.Response> updateMovie(
        MoviesRPC.Movie request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateMovieMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Delete
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<MoviesRPC.Response> deleteMovie(
        MoviesRPC.MovieName request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
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
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateMovieMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              MoviesRPC.Movie,
              MoviesRPC.Response>(
                service, METHODID_CREATE_MOVIE)))
        .addMethod(
          getGetMovieMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              MoviesRPC.MovieName,
              MoviesRPC.Response>(
                service, METHODID_GET_MOVIE)))
        .addMethod(
          getGetMoviesByActorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              MoviesRPC.MovieFilters,
              MoviesRPC.Response>(
                service, METHODID_GET_MOVIES_BY_ACTOR)))
        .addMethod(
          getGetMoviesByCategoryMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              MoviesRPC.MovieFilters,
              MoviesRPC.Response>(
                service, METHODID_GET_MOVIES_BY_CATEGORY)))
        .addMethod(
          getUpdateMovieMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              MoviesRPC.Movie,
              MoviesRPC.Response>(
                service, METHODID_UPDATE_MOVIE)))
        .addMethod(
          getDeleteMovieMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              MoviesRPC.MovieName,
              MoviesRPC.Response>(
                service, METHODID_DELETE_MOVIE)))
        .build();
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
    private final java.lang.String methodName;

    MovieMethodsMethodDescriptorSupplier(java.lang.String methodName) {
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
