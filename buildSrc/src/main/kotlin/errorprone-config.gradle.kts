import net.ltgt.gradle.errorprone.errorprone
import net.ltgt.gradle.errorprone.ErrorPronePlugin

apply<ErrorPronePlugin>()

tasks.withType(JavaCompile::class).configureEach {
    // Workaround for ErrorProne
    // See https://github.com/tbroyer/gradle-errorprone-plugin/issues/11#issuecomment-429546366
    options.compilerArgs.remove("-proc:none")
    // Enable Java warnings
    options.compilerArgs = options.compilerArgs + listOf(
        "-Xlint:cast",
        // Disable deprecation warning to not clutter build log
        // "-Xlint:deprecation",
        "-Xlint:divzero",
        "-Xlint:empty",
        "-Xlint:fallthrough",
        "-Xlint:finally",
        "-Xlint:overrides",
        "-Xlint:path",
        // We use serializable objects in-process only as alternative to parcelable
        // So we don"t have to worry about compiler compatibility and can safely omit declaration of
        // serialVersionUID
        // "-Xlint:serial",
        "-Xlint:unchecked"
    )
    // Error Prone configuration
    // See http://errorprone.info/docs/flags
    // See http://errorprone.info/bugpatterns
    options.errorprone.errorproneArgs.addAll(listOf(
        "-Xep:BadAnnotationImplementation:ERROR",
        "-Xep:BoxedPrimitiveConstructor:ERROR",
        "-Xep:ClassCanBeStatic:WARN",
        "-Xep:DefaultCharset:ERROR",
        "-Xep:EqualsHashCode:ERROR",
        "-Xep:EqualsIncompatibleType:ERROR",
        "-Xep:Finally:ERROR",
        "-Xep:ImmutableAnnotationChecker:ERROR",
        "-Xep:ImmutableEnumChecker:ERROR",
        "-Xep:IncrementInForLoopAndHeader:OFF",
        "-Xep:IterableAndIterator:ERROR",
        "-Xep:MissingOverride:WARN",
        "-Xep:NarrowingCompoundAssignment:ERROR",
        "-Xep:NonOverridingEquals:ERROR",
        "-Xep:NullableConstructor:ERROR",
        "-Xep:NullablePrimitive:ERROR",
        "-Xep:NullableVoid:ERROR",
        "-Xep:TypeParameterShadowing:ERROR",
        "-Xep:URLEqualsHashCode:ERROR",
        "-Xep:ComparisonContractViolated:ERROR",
        "-Xep:DivZero:ERROR",
        "-Xep:EmptyIf:ERROR",
        "-Xep:NumericEquality:ERROR",
        "-Xep:HardCodedSdCardPath:ERROR",
        "-Xep:MissingDefault:ERROR",
        "-Xep:StaticQualifiedUsingExpression:ERROR",
        "-Xep:StringEquality:ERROR",
        "-Xep:RedundantThrows:ERROR",
        "-Xep:MixedArrayDimensions:ERROR"
    ))
    options.errorprone.disableWarningsInGeneratedCode = true
}